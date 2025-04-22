package com.ecoalerta.app.infra.exceptions;

public class EmailCadastradoException extends RuntimeException {

    public EmailCadastradoException() {super("E-mail já cadastrado");}

    public EmailCadastradoException(String message) {
        super(message);
    }
}