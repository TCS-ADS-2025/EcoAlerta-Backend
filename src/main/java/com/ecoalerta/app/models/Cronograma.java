package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cronograma extends EntityID{

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    @OneToMany(mappedBy = "cronograma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bairro> bairros = new ArrayList<>();
}
