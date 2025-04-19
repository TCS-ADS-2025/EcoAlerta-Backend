package com.ecoalerta.app.infra.exceptions;

public class UsuarioSemMensagemException extends RuntimeException {

    public UsuarioSemMensagemException() { super("Usuário informado não possui mensagens");}

    public UsuarioSemMensagemException(String message) {
        super(message);
    }
}