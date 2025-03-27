package com.ecoalerta.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends EntityID {

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @JsonIgnore
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Mensagem> mensagens = new ArrayList<>();

    public Usuario(String nomeCompleto, Endereco endereco, String email, String senha) {
        super();
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }
}