package com.ecoalerta.app.dto.usuario;

import com.ecoalerta.app.models.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UsuarioRequestDTO(

        @NotBlank(message = "Nome é um campo obrigatório!")
        @Size(min = 3, message = "Nome deve ter no mínimo 3 letras!")
        String nomeCompleto,

        @NotBlank(message = "E-mail é um campo obrigatório!")
        @Email
        String email,

        @NotBlank(message = "CEP é obrigatório!")
        @Size(min = 8, max = 9, message = "CEP deve ter 8 caracteres!")
        String cep,

        UUID bairroId,

        @Size(max = 100, message = "Limite máximo de 100 caractéres!")
        String logradouro,

        @Size(max = 100, message = "Limite máximo de 100 caractéres!")
        String numero,

        @Size(max = 100, message = "Limite máximo de 100 caractéres!")
        String complemento,

        @NotBlank(message = "Senha é um campo obrigatório!")
        @Size(min = 8, message = "Senha deve ter no mínimo 8 caractéres!")
        String senha,

        Role role
) { }