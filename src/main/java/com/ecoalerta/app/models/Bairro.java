package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.Coleta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bairro extends EntityID{

    @OneToOne(mappedBy = "bairro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "cronograma_id", nullable = false)
    private Cronograma cronograma;

    @Column
    private String nomeBairro;

    @Enumerated(EnumType.STRING)
    private Coleta coleta;
}
