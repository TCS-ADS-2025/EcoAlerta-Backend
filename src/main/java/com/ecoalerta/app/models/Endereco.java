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
public class Endereco extends EntityID{

    @OneToOne
    private Bairro bairro;

    @OneToOne(mappedBy = "endereco")
    private Usuario usuario;

    @Column
    private String cep;

    @Column
    private String cidade;

    @Column
    private String logradouro;

    @Column
    private Integer numero;

    @Column
    private String complemento;
}
