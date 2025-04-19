package com.ecoalerta.app.dto.usuario;

import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;

import java.util.List;
import java.util.UUID;

public record UsuarioResponseDTO(

        UUID id,
        String nomeCompleto,
        String email,
        Endereco endereco,
        List<Mensagem> mensagens
) {

    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getEndereco(),
                usuario.getMensagens()
        );
    }
}