package com.ecoalerta.app.models;

import com.ecoalerta.app.enums.Status;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    private Usuarios usuarios;

    private Status status;

    @Column
    private String titulo;

    @Column
    private String destinatario;

    @Column
    private String mensagem;
}
