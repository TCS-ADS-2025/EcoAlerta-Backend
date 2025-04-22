package com.ecoalerta.app.dto.usuario;

import jakarta.validation.constraints.Email;

public record LoginRequestDTO(@Email String email, String senha) { }