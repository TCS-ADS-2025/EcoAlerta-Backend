package com.ecoalerta.app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bairros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bairro extends EntityID{

    @Column(nullable = false)
    private String nomeBairro;

    @OneToMany(mappedBy = "bairro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Endereco> enderecos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cronograma_id", referencedColumnName = "id")
    @JsonManagedReference
    private Cronograma cronograma;
}