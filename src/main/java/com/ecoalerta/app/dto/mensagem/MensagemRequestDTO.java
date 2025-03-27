package com.ecoalerta.app.dto.mensagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record MensagemRequestDTO (

        @NotBlank(message = "Título é um campo obrigatório!")
        @Size(max = 50)
        String titulo,

        @NotBlank(message = "Destinatário é um campo obrigatório!")
        @Size(max = 100)
        String destinatario,

        @NotBlank(message = "Mensagem é um campo obrigatório!")
        @Size(max = 250)
        String mensagem,

        UUID usuarioId
) {
}