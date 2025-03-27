package com.ecoalerta.app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Mensagens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem extends EntityID{

    @Column
    private Boolean status;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false)
    private String mensagem;

    @Column(name = "data_hora")
    private LocalDateTime dataHora = LocalDateTime
            .parse(LocalDateTime.now()
            .format(DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")));

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    public Mensagem(String titulo, String destinatario, String mensagem, Usuario usuario) {
        super();
        this.titulo = titulo;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.usuario = usuario;
    }
}