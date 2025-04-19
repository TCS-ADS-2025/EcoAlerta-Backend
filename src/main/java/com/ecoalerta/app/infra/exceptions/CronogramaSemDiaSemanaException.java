package com.ecoalerta.app.infra.exceptions;

public class CronogramaSemDiaSemanaException extends RuntimeException {

    public CronogramaSemDiaSemanaException() {super("Cronograma não possui dias cadastrados");}

    public CronogramaSemDiaSemanaException(String message) {
        super(message);
    }
}
