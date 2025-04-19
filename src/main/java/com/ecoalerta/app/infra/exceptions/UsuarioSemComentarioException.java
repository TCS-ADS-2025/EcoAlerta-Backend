package com.ecoalerta.app.infra.exceptions;

public class UsuarioSemComentarioException extends RuntimeException {

    public UsuarioSemComentarioException() { super("Usuário informado não possui comentários");}

    public UsuarioSemComentarioException(String message) {
        super(message);
    }
}