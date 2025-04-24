package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.FilaEmail.FilaEmailDTO;
import com.ecoalerta.app.dto.mensagem.MensagemResponseDTO;
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

    @Transactional
    public void enviarParaTodosUsuarios(String titulo, String corpoMensagem) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Mensagem> mensagens = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            String mensagemTexto = "Olá " + usuario.getNomeCompleto() +
                    ",\n\n" + corpoMensagem + "\n\nAtenciosamente, Equipe Eco Alerta";

            Mensagem mensagem = new Mensagem();
            mensagem.setTitulo(titulo);
            mensagem.setDestinatario(usuario.getEmail());
            mensagem.setMensagem(mensagemTexto);
            mensagem.setStatus(false);
            mensagem.setDataHora(LocalDateTime.now());
            mensagem.setUsuario(usuario);

            mensagens.add(mensagem);

            emailService.enfileirarEmail(new FilaEmailDTO(mensagem));
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

            Mensagem mensagem = new Mensagem();
            mensagem.setTitulo(titulo);
            mensagem.setDestinatario(usuario.getEmail());
            mensagem.setMensagem(mensagemTexto);
            mensagem.setStatus(false);
            mensagem.setDataHora(LocalDateTime.now());
            mensagem.setUsuario(usuario);

            mensagens.add(mensagem);

            emailService.enfileirarEmail(new FilaEmailDTO(mensagem));
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