package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem extends EntityID{

    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String titulo;

    @Column
    private String destinatario;

    @Column
    private String mensagem;
}
