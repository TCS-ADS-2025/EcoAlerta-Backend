package com.ecoalerta.app.dto.endereco;

import com.ecoalerta.app.models.Endereco;

import java.util.UUID;

public record EnderecoResponseDTO(
        UUID id,
        String cep,
        String uf,
        String localidade,
        UUID bairroId,
        String nomeBairro,
        String logradouro,
        String complemento,
        String numero,
        UUID usuarioId,
        String nomeUsuario
) {

    public static EnderecoResponseDTO fromEntity(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getUf(),
                endereco.getLocalidade(),
                endereco.getBairro() != null ? endereco.getBairro().getId() : null,
                endereco.getBairro() != null ? endereco.getBairro().getNomeBairro() : null,
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getNumero(),
                endereco.getUsuario() != null ? endereco.getUsuario().getId() : null,
                endereco.getUsuario() != null ? endereco.getUsuario().getNomeCompleto() : null
        );
    }
}