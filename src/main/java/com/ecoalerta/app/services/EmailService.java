package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.FilaEmail.FilaEmailDTO;
import com.ecoalerta.app.infra.exceptions.EmailNaoEnviadoException;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.repositories.MensagemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final MensagemRepository mensagemRepository;
    private final BlockingQueue<FilaEmailDTO> filaEmails = new LinkedBlockingQueue<>();

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmail(String destinatario, String titulo, String mensagem) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(titulo);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            return "E-mail Enviado!";
        } catch (Exception e) {
            throw new EmailNaoEnviadoException();
        }
    }

    public void enfileirarEmail(FilaEmailDTO dto) {
        filaEmails.offer(dto);
    }

    @Transactional
    @Scheduled(cron = "0 */1 * * * *")
    public void processarFilaEmails() {
        while (!filaEmails.isEmpty()) {
            FilaEmailDTO dto = filaEmails.poll();
            Mensagem mensagem = dto.mensagem();

            try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom(remetente);
                simpleMailMessage.setTo(mensagem.getDestinatario());
                simpleMailMessage.setSubject(mensagem.getTitulo());
                simpleMailMessage.setText(mensagem.getMensagem());
                javaMailSender.send(simpleMailMessage);

                mensagem.setStatus(true);
                mensagemRepository.save(mensagem);

            } catch (Exception e) {
                System.err.println("Erro ao enviar e-mail para " + mensagem.getDestinatario() + ": " + e.getMessage());
            }
        }
    }
}