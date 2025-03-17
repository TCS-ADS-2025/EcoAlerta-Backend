package models;

import enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    private Usuarios usuarios;

    private Status status;

    @Column
    private String titulo;

    @Column
    private String destinatario;

    @Column
    private String mensagem;

    public Mensagem(int id, Usuarios usuarios, Status status, String titulo, String destinatario, String mensagem) {
        this.id = id;
        this.usuarios = usuarios;
        this.status = status;
        this.titulo = titulo;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "id=" + id +
                ", usuarios=" + usuarios +
                ", status=" + status +
                ", titulo='" + titulo + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}
