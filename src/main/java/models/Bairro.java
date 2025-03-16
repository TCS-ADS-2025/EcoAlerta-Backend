package models;

import enums.Coleta;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    @Column
    private String nomeBairro;

    private Coleta coleta;

    public Bairro(int id, String nomeBairro, Coleta coleta) {
        this.id = id;
        this.nomeBairro = nomeBairro;
        this.coleta = coleta;
    }

    @Override
    public String toString() {
        return "Bairro{" +
                "id=" + id +
                ", nomeBairro='" + nomeBairro + '\'' +
                ", coleta=" + coleta +
                '}';
    }
}
