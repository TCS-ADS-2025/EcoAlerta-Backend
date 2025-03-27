package com.ecoalerta.app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends EntityID{

    @Column(nullable = false)
    private String cep;

    @Column()
    private String logradouro;

    @Column
    private String localidade;

    @Column
    private String numero;

    @Column
    private String complemento;

    @Column
    private String uf;

    @OneToOne(mappedBy = "endereco")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "bairro_id", referencedColumnName = "id")
    @JsonManagedReference
    private Bairro bairro;
}