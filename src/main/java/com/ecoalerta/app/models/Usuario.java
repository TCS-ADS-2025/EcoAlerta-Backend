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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @JsonIgnore
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Mensagem> mensagens = new ArrayList<>();

    @NotBlank(message = "Nome é um campo obrigatório!")
    @Size(min = 3, message = "o nome deve conter no mínimo 3 caractéres!")
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @NotBlank(message = "E-mail é um campo obrigatório!")
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha é um campo obrigatório!")
    @Size(min = 8, message = "A senha deve conter no mínimo 8 caractéres!")
    @Column(nullable = false)
    private String senha;

    public Usuario(String nomeCompleto, Endereco endereco, String email, String senha) {
        super();
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }
}
