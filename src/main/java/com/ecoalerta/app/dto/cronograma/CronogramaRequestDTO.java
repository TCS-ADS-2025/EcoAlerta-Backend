package com.ecoalerta.app.dto.cronograma;

import com.ecoalerta.app.models.enums.DiaSemana;

import java.util.List;
import java.util.UUID;

public record CronogramaRequestDTO(
        DiaSemana diaSemana,
        List<UUID> bairrosIds
) {
}
