package com.ecoalerta.app.infra.exceptions;

public class CategoriaSemComentarioException extends RuntimeException {

    public CategoriaSemComentarioException() { super("Categoria informada não possui comentários");}

    public CategoriaSemComentarioException(String message) {
        super(message);
    }
}
