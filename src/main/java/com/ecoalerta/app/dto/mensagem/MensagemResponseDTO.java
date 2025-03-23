package com.ecoalerta.app.dto.mensagem;

import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.enums.Status;

import java.time.LocalDateTime;

public record MensagemResponseDTO(

        Status status,
        String titulo,
        String destinario,
        String mensagem,
        LocalDateTime dataHora
) {

    public static MensagemResponseDTO fromEntity(Mensagem mensagem){
        return new MensagemResponseDTO(
                mensagem.getStatus(),
                mensagem.getTitulo(),
                mensagem.getDestinatario(),
                mensagem.getMensagem(),
                mensagem.getDataHora()
        );
    }
}
