package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.mensagem.MensagemRequestDTO;
import com.ecoalerta.app.dto.mensagem.MensagemResponseDTO;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.repositories.MensagemRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;

    public MensagemResponseDTO criar(MensagemRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Mensagem mensagem = new Mensagem();
        mensagem.setTitulo(request.titulo());
        mensagem.setDestinatario(request.destinatario());
        mensagem.setMensagem(request.mensagem());
        mensagem.setUsuario(usuario);

        Mensagem salvo = mensagemRepository.save(mensagem);

        return MensagemResponseDTO.fromEntity(salvo);
    }

    public List<MensagemResponseDTO> listarTodos(){
        return mensagemRepository.findAll()
                .stream()
                .map(MensagemResponseDTO::fromEntity)
                .toList();
    }
}