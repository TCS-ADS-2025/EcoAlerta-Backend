package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.Coleta;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bairro extends EntityID{

    @Column
    private String nomeBairro;

    @Enumerated(EnumType.STRING)
    private Coleta coleta;
}
