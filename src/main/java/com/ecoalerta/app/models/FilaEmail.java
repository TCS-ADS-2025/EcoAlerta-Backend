package com.ecoalerta.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "gerenciador_email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilaEmail extends EntityID{

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private Boolean enviado = false;

    @Column(name = "data_hora")
    private LocalDateTime dataHora = LocalDateTime.now();
}