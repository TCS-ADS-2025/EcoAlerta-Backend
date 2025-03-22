package com.ecoalerta.app.dto.mensagem;

import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MensagemRequestDTO (

    @NotBlank(message = "Título é um campo obrigatório!")
    @Size(max = 50)
    String titulo,

    @NotBlank(message = "Destinatário é um campo obrigatório!")
    @Size(max = 100)
    String destinatario,

    @NotBlank(message = "Mensagem é um campo obrigatório!")
    @Size(max = 250)
    String mensagem,

    Usuario usuario
) {

    public Mensagem toEntity(){
        return new Mensagem(
                this.titulo,
                this.destinatario,
                this.mensagem,
                this.usuario
        );
    }
}
