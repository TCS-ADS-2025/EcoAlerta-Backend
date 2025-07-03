package com.ecoalerta.app.dto.usuario;

import com.ecoalerta.app.models.enums.Role;

import java.util.UUID;

public record LoginResponseDTO(String token, Role role, UUID userId) { }