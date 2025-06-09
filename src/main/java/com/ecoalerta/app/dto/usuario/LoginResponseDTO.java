package com.ecoalerta.app.dto.usuario;

import com.ecoalerta.app.models.enums.Role;

public record LoginResponseDTO(String token, Role role) { }