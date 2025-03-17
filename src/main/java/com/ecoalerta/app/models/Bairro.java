package com.ecoalerta.app.models;

import com.ecoalerta.app.enums.Coleta;
import jakarta.persistence.Column;
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

    private Coleta coleta;
}
