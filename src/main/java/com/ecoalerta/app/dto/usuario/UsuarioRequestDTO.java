package com.ecoalerta.app.dto.usuario;

import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Usuario;

public record UsuarioRequestDTO(

    String nomeCompleto,
    Endereco endereco,
    String email,
    String senha
    ) {

    public Usuario toEntity() {
        return new Usuario(
                this.nomeCompleto,
                this.endereco,
                this.email,
                this.senha
        );
    }
}
