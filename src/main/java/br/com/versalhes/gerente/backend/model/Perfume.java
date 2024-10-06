package br.com.versalhes.gerente.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Perfume")
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Perfume")
    private long idPerfume;

    @Column(name = "Id_Marca")
    private long idMarca;

    @Column(name = "Id_Tipo")
    private long idTipo;

    @Column(name = "Genero")
    private char genero;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Especial")
    private boolean especial;

    @Column(name = "MediaAvaliacao")
    private byte mediaAvaliacao;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "PrecoNormal")
    private double precoNormal;

    @Column(name = "Oferta")
    private boolean oferta;

    @Column(name = "PrecoOferta")
    private double precoOferta;

    @Column(name = "Estoque")
    private long estoque;

    public long getIdPerfume() {
        return idPerfume;
    }

    public void setIdPerfume(long idPerfume) {
        this.idPerfume = idPerfume;
    }

    public long getIdMarca() {
        return idMarca;
    }

    public void setId_Marca(long idMarca) {
        this.idMarca = idMarca;
    }

    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this. nome = nome;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public byte getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(byte mediaAvaliacao) {
        this. mediaAvaliacao = mediaAvaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoNormal() {
        return precoNormal;
    }

    public void setPrecoNormal(double precoNormal) {
        this.precoNormal = precoNormal;
    }

    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    public double getPrecoOferta() {
        return precoOferta;
    }

    public void setPrecoOferta(double precoOferta) {
        this. precoOferta = precoOferta;
    }

    public long getEstoque() {
        return estoque;
    }

    public void setEstoque(long estoque) {
        this.estoque = estoque;
    }

}
