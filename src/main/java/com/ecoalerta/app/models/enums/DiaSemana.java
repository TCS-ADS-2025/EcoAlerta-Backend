package com.ecoalerta.app.models.enums;

import java.time.DayOfWeek;

public enum DiaSemana {

    SEGUNDA("Segunda"),
    TERCA("Terça"),
    QUARTA("Quarta"),
    QUINTA("Quinta"),
    SEXTA("Sexta"),
    SABADO("Sábado");

    private String dia;

    DiaSemana(String dia) {
        this.dia = dia;
    }

    public String getDia() {
        return dia;
    }

    public static DiaSemana fromDayOfWeek(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> SEGUNDA;
            case TUESDAY -> TERCA;
            case WEDNESDAY -> QUARTA;
            case THURSDAY -> QUINTA;
            case FRIDAY -> SEXTA;
            case SATURDAY -> SABADO;
            case SUNDAY -> null;
        };
    }
}