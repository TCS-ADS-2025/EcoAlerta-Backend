package com.ecoalerta.app.dto.usuario;

import com.ecoalerta.app.models.Endereco;

import java.util.List;
import java.util.UUID;

public record UsuarioResponseDTO(

        UUID id,
        List<UUID> mensagemIds,
        String nomeCompleto,
        String email,
        Endereco endereco
) {
}

