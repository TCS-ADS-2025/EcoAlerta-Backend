package com.ecoalerta.app.services;

import com.ecoalerta.app.dto.comentario.ComentarioRequestDTO;
import com.ecoalerta.app.dto.comentario.ComentarioResponseDTO;
import com.ecoalerta.app.infra.exceptions.CategoriaSemComentarioException;
import com.ecoalerta.app.infra.exceptions.UsuarioSemComentarioException;
import com.ecoalerta.app.models.Comentario;
import com.ecoalerta.app.models.Usuario;
import com.ecoalerta.app.models.enums.CategoriaComentario;
import com.ecoalerta.app.repositories.ComentarioRepository;
import com.ecoalerta.app.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;

    public Comentario criar(ComentarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Comentario comentario = new Comentario();
        comentario.setTexto(request.texto());
        comentario.setCategoriaComentario(request.categoriaComentario());
        comentario.setDataHora(LocalDateTime.now());
        comentario.setUsuario(usuario);

        return comentarioRepository.save(comentario);
    }

    public List<ComentarioResponseDTO> listarTodos() {
        return comentarioRepository.findAll()
                .stream()
                .map(ComentarioResponseDTO::fromEntity)
                .toList();
    }

    public List<ComentarioResponseDTO> listarPorCategoria(CategoriaComentario categoriaComentario) {
        List<Comentario> comentarios = comentarioRepository.findByCategoriaComentario(categoriaComentario);

        if (comentarios.isEmpty()) {
            throw new CategoriaSemComentarioException();
        }

        return comentarios.stream()
                .map(ComentarioResponseDTO::fromEntity)
                .toList();
    }

    public List<ComentarioResponseDTO> listarPorUsuario(UUID usuarioId) {
        List<Comentario> comentarios = comentarioRepository.findByUsuarioId(usuarioId);

        if (comentarios.isEmpty()){
            throw new UsuarioSemComentarioException();
        }

        return comentarios.stream()
                .map(ComentarioResponseDTO::fromEntity)
                .toList();
    }

    public void excluir(UUID id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado"));

        comentarioRepository.delete(comentario);
    }
}