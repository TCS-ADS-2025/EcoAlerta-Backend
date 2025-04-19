package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.mensagem.MensagemRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemResponseDTO;
import com.ecoalerta.app.infra.exceptions.EmailNaoEnviadoException;
import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.repositories.MensagemRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    public Mensagem criar(MensagemRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Mensagem mensagem = new Mensagem();
        mensagem.setTitulo(request.titulo());
        mensagem.setDestinatario(request.destinatario());
        mensagem.setMensagem(request.mensagem());
        mensagem.setUsuario(usuario);

        return mensagemRepository.save(mensagem);
    }

    @Transactional
    public void enviarParaTodosUsuarios(String titulo, String corpoMensagem) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Mensagem> mensagens = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            String mensagemTexto = "Olá " + usuario.getNomeCompleto() +
                    ",\n\n" + corpoMensagem + "\n\nAtenciosamente, Equipe Eco Alerta";

            boolean status;

            try {
                emailService.enviarEmail(usuario.getEmail(), titulo, mensagemTexto);
                status = true;
            } catch (Exception e) {
                status = false;
                throw new EmailNaoEnviadoException();
            }

            Mensagem mensagem = new Mensagem();
            mensagem.setTitulo(titulo);
            mensagem.setDestinatario(usuario.getEmail());
            mensagem.setMensagem(mensagemTexto);
            mensagem.setStatus(status);
            mensagem.setDataHora(LocalDateTime.now());
            mensagem.setUsuario(usuario);

            mensagens.add(mensagem);
        }

        mensagemRepository.saveAll(mensagens);
    }

    @Transactional
    public void enviarParaBairros(List<Bairro> bairros, String titulo, String corpoMensagem) {
        List<Usuario> usuarios = usuarioRepository.findByEnderecoBairroIn(bairros);
        List<Mensagem> mensagens = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            String mensagemTexto = "Olá " + usuario.getNomeCompleto() +
                    ",\n\n" + corpoMensagem + "\n\nAtenciosamente, Equipe Eco Alerta";

            boolean status;

            try {
                emailService.enviarEmail(usuario.getEmail(), titulo, mensagemTexto);
                status = true;
            } catch (Exception e) {
                status = false;
                throw new EmailNaoEnviadoException();
            }

            Mensagem mensagem = new Mensagem();
            mensagem.setTitulo(titulo);
            mensagem.setDestinatario(usuario.getEmail());
            mensagem.setMensagem(mensagemTexto);
            mensagem.setStatus(status);
            mensagem.setDataHora(LocalDateTime.now());
            mensagem.setUsuario(usuario);

            mensagens.add(mensagem);
        }

        mensagemRepository.saveAll(mensagens);
    }

    public List<MensagemResponseDTO> listarTodos(){
        return mensagemRepository.findAll()
                .stream()
                .map(MensagemResponseDTO::fromEntity)
                .toList();
    }

    public Mensagem listarPorId(UUID id) {
        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensagem não encontrada"));

        return mensagem;
    }

    public List<MensagemResponseDTO> listarPorUsuario(UUID usuarioId) {
        return mensagemRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(MensagemResponseDTO::fromEntity)
                .toList();
    }
}