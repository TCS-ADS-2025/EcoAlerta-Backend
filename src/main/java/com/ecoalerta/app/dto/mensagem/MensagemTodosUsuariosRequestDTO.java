package com.ecoalerta.app.dto.mensagem;

import jakarta.validation.constraints.NotBlank;

public record MensagemTodosUsuariosRequestDTO(
        @NotBlank(message = "Título é um campo obrigatório")
        String titulo,

        @NotBlank(message = "Mensagem é um campo obrigatório")
        String mensagem
) { }