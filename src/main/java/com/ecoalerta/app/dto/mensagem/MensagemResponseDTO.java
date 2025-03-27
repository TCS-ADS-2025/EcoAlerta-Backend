package com.ecoalerta.app.dto.mensagem;

import com.ecoalerta.app.models.Mensagem;
import com.ecoalerta.app.models.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record MensagemResponseDTO(

        UUID id,
        Boolean status,
        String titulo,
        String destinatario,
        String mensagem,
        LocalDateTime dataHora,
        Usuario usuario
) {

    public static MensagemResponseDTO fromEntity(Mensagem mensagem){
        return new MensagemResponseDTO(
                mensagem.getId(),
                mensagem.getStatus(),
                mensagem.getTitulo(),
                mensagem.getDestinatario(),
                mensagem.getMensagem(),
                mensagem.getDataHora(),
                mensagem.getUsuario()
        );
    }
}