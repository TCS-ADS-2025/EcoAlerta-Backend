package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.CategoriaComentario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comentario extends EntityID{

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaComentario categoriaComentario;

    @Column
    private LocalDateTime dataHora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}