package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.DiaSemana;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cronogramas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cronograma extends EntityID{

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    @OneToMany(mappedBy = "cronograma", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    @OrderBy("nomeBairro ASC")
    private List<Bairro> bairros = new ArrayList<>();
}