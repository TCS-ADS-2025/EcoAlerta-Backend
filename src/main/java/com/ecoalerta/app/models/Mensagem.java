package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem extends EntityID{

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String titulo;

    @Column
    private String destinatario;

    @Column
    private String mensagem;

    @Column
    private LocalDateTime dataHora;
}
