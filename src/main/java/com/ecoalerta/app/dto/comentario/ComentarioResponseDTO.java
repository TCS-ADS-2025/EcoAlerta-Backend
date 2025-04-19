package com.ecoalerta.app.dto.comentario;

import com.ecoalerta.app.models.Comentario;
import com.ecoalerta.app.models.enums.CategoriaComentario;

import java.time.LocalDateTime;
import java.util.UUID;

public record ComentarioResponseDTO(
        UUID id,
        String texto,
        CategoriaComentario categoriaComentario,
        LocalDateTime dataHora,
        UUID usuarioId,
        String nomeUsuario
) {
    public static ComentarioResponseDTO fromEntity(Comentario comentario) {
        return new ComentarioResponseDTO(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getCategoriaComentario(),
                comentario.getDataHora(),
                comentario.getUsuario().getId(),
                comentario.getUsuario().getNomeCompleto()
        );
    }
}