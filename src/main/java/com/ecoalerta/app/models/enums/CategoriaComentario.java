package com.ecoalerta.app.models.enums;

public enum CategoriaComentario {

    ELOGIO("Elogio"),
    CRITICA("Crítica"),
    SUGESTAO("Sugestão"),
    RECLAMACAO("Reclamação"),
    DUVIDA("Dúvida"),
    FALTA_DE_COLETA("Falta de Coleta"),
    INFORMACAO_ERRADA("Informação Errada"),
    OUTRO("Outro");

    private String categoria;

    CategoriaComentario(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}