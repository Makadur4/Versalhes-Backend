package br.com.versalhes.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Frete")
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "UF")
    private String uf;

    @Column(name = "Prazo")
    private short prazo;

    @Column(name = "Valor")
    private double valor;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUF() {
        return uf;
    }
    public void setUF(String uf) {
        this.uf = uf;
    }

    public short getPrazo() {
        return prazo;
    }
    public void setPrazo(short prazo) {
        this.prazo = prazo;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}
