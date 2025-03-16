package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuarios extends EntityID {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private int id;
    @Column
    private String nomeCompleto;
    @Column
    private String email;
    @Column
    private String senha;

    @Override
    public String toString() {
        return "CadastroUsuarios{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
