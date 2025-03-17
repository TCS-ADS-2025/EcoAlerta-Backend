package com.ecoalerta.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends EntityID {

    @Column
    private String nomeCompleto;
    @Column
    private String email;
    @Column
    private String senha;
}
