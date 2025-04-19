package com.ecoalerta.app.dto.comentario;

import com.ecoalerta.app.models.enums.CategoriaComentario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record ComentarioRequestDTO(
        UUID usuarioId,

        @NotBlank(message = "Texto é um campo obrigatório!")
        @Size(max = 100)
        String texto,

        @NotNull(message = "Categoria é um campo obrigatório")
        CategoriaComentario categoriaComentario,

        LocalDateTime dataHora
) { }