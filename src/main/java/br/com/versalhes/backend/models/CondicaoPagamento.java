package br.com.versalhes.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "CondicaoPagamento")
public class CondicaoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "QuantidadeParcelas")
    private short quantidadeParcelas;

    @Column(name = "PercentualAcrescimo")
    private double percentualAcrescimo;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public short getQuantidadeParcelas() {
        return quantidadeParcelas;
    }
    public void setQuantidadeParcelas(short quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public double getPercentualAcrescimo() {
        return percentualAcrescimo;
    }
    public void setPercentualAcrescimo(double percentualAcrescimo) {
        this.percentualAcrescimo = percentualAcrescimo;
    }
}
