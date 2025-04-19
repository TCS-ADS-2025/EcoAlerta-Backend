package com.ecoalerta.app.infra.exceptions;

public class EmailNaoEnviadoException extends RuntimeException {

    public EmailNaoEnviadoException() {super("Erro ao enviar o e-mail");}

    public EmailNaoEnviadoException(String message) {
        super(message);
    }
}
