package com.ecoalerta.app.dto.usuario;


public record UsuarioRequestDTO(

    String nomeCompleto,
    String email,
    String senha
    ) {
}
