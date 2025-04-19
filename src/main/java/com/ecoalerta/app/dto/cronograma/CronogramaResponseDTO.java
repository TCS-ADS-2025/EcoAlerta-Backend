package com.ecoalerta.app.dto.cronograma;

import com.ecoalerta.app.models.Bairro;
import com.ecoalerta.app.models.Cronograma;
import com.ecoalerta.app.models.enums.DiaSemana;

import java.util.List;
import java.util.UUID;

public record CronogramaResponseDTO(
        UUID id,
        DiaSemana diaSemana,
        List<Bairro> bairros
) {

    public static CronogramaResponseDTO fromEntity(Cronograma cronograma){
        return new CronogramaResponseDTO(
                cronograma.getId(),
                cronograma.getDiaSemana(),
                cronograma.getBairros()
        );
    }
}
