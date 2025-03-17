package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.Coleta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Bairros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bairro extends EntityID{

    @OneToOne(mappedBy = "bairro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "cronograma_id", nullable = false)
    @JsonBackReference
    private Cronograma cronograma;

    @NotBlank(message = "Nome do Bairro é um campo obrigatório!")
    @Column(name = "nome_bairro", nullable = false)
    private String nomeBairro;

    @Enumerated(EnumType.STRING)
    private Coleta coleta;
}
