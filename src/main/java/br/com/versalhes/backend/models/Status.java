package br.com.versalhes.backend.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    EFETIVADO(1),
    APROVADO(2),
    DESPACHADO(3),
    ENTREGUE(4),
    CANCELADO(5);

    private final int valor;

    Status(int valor) {
        this.valor = valor;
    }

    @JsonValue
    public int getValor() {
        return valor;
    }
}