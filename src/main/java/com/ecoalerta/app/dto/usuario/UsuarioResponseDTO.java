package com.ecoalerta.app.dto.usuario;

import com.ecoalerta.app.dto.endereco.EnderecoResponseDTO;
import com.ecoalerta.app.models.Endereco;
import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;

import java.util.List;
import java.util.UUID;

public record UsuarioResponseDTO(

        UUID id,
        String nomeCompleto,
        String email,
        EnderecoResponseDTO endereco,
        List<Mensagem> mensagens
) {

    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                EnderecoResponseDTO.fromEntity(usuario.getEndereco()),
                usuario.getMensagens()
        );
    }
}