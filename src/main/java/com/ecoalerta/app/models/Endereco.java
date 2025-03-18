package com.ecoalerta.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @OneToOne
    @JoinColumn(name = "bairro_id", referencedColumnName = "id")
    private Bairro bairro;

    @OneToOne(mappedBy = "endereco")
    private Usuario usuario;

    @NotBlank(message = "CEP é um campo obrigatório!")
    @Size(min = 8, max = 8)
    @Column(nullable = false)
    private String cep;

    @NotBlank(message = "Cidade é um campo obrigatório!")
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Logradouro é um campo obrigatório!")
    @Column(nullable = false)
    private String logradouro;

    @Size(max = 10)
    @Column(nullable = true)
    private Integer numero;

    @Size(max = 100)
    @Column(nullable = true)
    private String complemento;
}
