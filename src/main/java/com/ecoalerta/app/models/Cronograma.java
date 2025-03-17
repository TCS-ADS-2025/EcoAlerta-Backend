package com.ecoalerta.app.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cronograma extends EntityID{

    @Column
    private String diaSemana;

    private Bairro bairro;
}
