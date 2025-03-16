package models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    @Column
    private String diaSemana;

    private Bairro bairro;

    public Cronograma(int id, String diaSemana, Bairro bairro) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Cronograma{" +
                "id=" + id +
                ", diaSemana='" + diaSemana + '\'' +
                ", bairro=" + bairro +
                '}';
    }
}
