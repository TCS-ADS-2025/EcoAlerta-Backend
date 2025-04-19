package com.ecoalerta.app.services;

import com.ecoalerta.app.infra.exceptions.EmailNaoEnviadoException;
import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Cronograma;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.models.enums.DiaSemana;
import com.ecoalerta.app.repositories.CronogramaRepository;
import com.ecoalerta.app.repositories.MensagemRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final CronogramaRepository cronogramaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final MensagemRepository mensagemRepository;

    @Transactional
    @Scheduled(cron = "0 */10 * * * *")
    public void notificaColeta() {
        DayOfWeek amanha = LocalDateTime.now().plusDays(1).getDayOfWeek();
        DiaSemana diaSemana = DiaSemana.fromDayOfWeek(amanha);

        List<Cronograma> cronogramas = cronogramaRepository.findByDiaSemana(diaSemana);
        List<Mensagem> mensagens = new ArrayList<>();

        for (Cronograma cronograma : cronogramas) {
            List<Bairro> bairros = cronograma.getBairros();
            List<Usuario> usuarios = usuarioRepository.findByEnderecoBairroIn(bairros);

            for (Usuario usuario : usuarios) {
                Bairro bairro = usuario.getEndereco().getBairro();
                String titulo = "Coleta amanhã!";
                String mensagemTexto = "Olá " + usuario.getNomeCompleto() +
                        ", a coleta no seu bairro " + bairro.getNomeBairro() + " está agendada para amanhã.";

                Boolean status;

                try {
                    emailService.enviarEmail(usuario.getEmail(), titulo, mensagemTexto);
                    status = true;
                } catch (Exception e) {
                    status = false;
                    throw new EmailNaoEnviadoException();
                }

                mensagens.add(new Mensagem(status, titulo, usuario.getEmail(), mensagemTexto, LocalDateTime.now(), usuario));
            }
        }
        mensagemRepository.saveAll(mensagens);
    }
}