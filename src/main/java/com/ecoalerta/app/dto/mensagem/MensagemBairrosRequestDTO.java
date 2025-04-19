package com.ecoalerta.app.dto.mensagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record MensagemBairrosRequestDTO(
        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "Mensagem é obrigatória")
        String mensagem,

        @NotEmpty(message = "Lista de bairros não pode estar vazia")
        List<UUID> bairrosIds
) { }