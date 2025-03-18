package com.ecoalerta.app.models;

import com.ecoalerta.app.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mensagens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem extends EntityID{

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotBlank(message = "Título é um campo obrigatório!")
    @Size(max = 50)
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "Destinatário é um campo obrigatório!")
    @Size(max = 50)
    @Column(nullable = false)
    private String destinatario;

    @NotBlank(message = "Mensagem é um campo obrigatório!")
    @Column(nullable = false)
    private String mensagem;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;
}
