package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    @Column
    private String cep;

    @Column
    private String cidade;

    @Column
    private String logradouro;

    @Column
    private int numero;

    @Column
    private String complemento;

    @OneToOne
    @JoinColumn
    private Usuarios usuarios;

    @OneToOne
    @JoinColumn
    private Bairro bairro;

    public Endereco(int id, String cep, String cidade, String logradouro, int numero, String complemento, Usuarios usuarios, Bairro bairro) {
        this.id = id;
        this.cep = cep;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.usuarios = usuarios;
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", usuarios=" + usuarios +
                ", bairro=" + bairro +
                '}';
    }
}
