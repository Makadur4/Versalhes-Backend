package br.com.versalhes.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Perfume")
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name="Marca_Id",nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name="Tipo_Id",nullable = false)
    private Tipo tipo;

    @Column(name = "Genero")
    private char genero;

    @Column(name = "Especial")
    private boolean especial;

    @Column(name = "MediaAvaliacao")
    private short mediaAvaliacao;

    @Column(name = "PrecoNormal")
    private double precoNormal;

    @Column(name = "Oferta")
    private boolean oferta;

    @Column(name = "PrecoOferta")
    private double precoOferta;

    @Column(name = "PrecoVenda")
    private double precoVenda;

    @Column(name = "Estoque")
    private long estoque;

    @OneToMany(mappedBy = "perfume")
    @JsonIgnore
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "perfume")
    @JsonIgnore
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "perfume")
    @JsonIgnore
    private List<ItemPedido> itensPedido;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this. nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }
    public void setId_Marca(Marca marca) {
        this.marca = marca;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setIdTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public char getGenero() {
        return genero;
    }
    public void setGenero(char genero) {
        this.genero = genero;
    }

    public boolean isEspecial() {
        return especial;
    }
    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public short getMediaAvaliacao() {
        return mediaAvaliacao;
    }
    public void setMediaAvaliacao(short mediaAvaliacao) {
        this. mediaAvaliacao = mediaAvaliacao;
    }

    public double getPrecoNormal() {
        return precoNormal;
    }
    public void setPrecoNormal(double precoNormal) {
        this.precoNormal = precoNormal;
    }

    public boolean getOferta() {
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

    public double getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(double precoVenda) {
        this. precoVenda = precoVenda;
    }

    public long getEstoque() {
        return estoque;
    }
    public void setEstoque(long estoque) {
        this.estoque = estoque;
    }

    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) { this.avaliacoes = avaliacoes; }

    @JsonIgnore
    public int getTotalAvaliacoes() { return avaliacoes.size(); }

    public List<Favorito> getFavoritos() { return favoritos; }
    public void setFavoritos(List<Favorito> favoritos) { this.favoritos = favoritos; }

    public List<ItemPedido> getItensPedido() { return itensPedido; }
    public void setItensPedido(List<ItemPedido> itensPedido) { this.itensPedido = itensPedido; }

    @JsonIgnore
    public int getTotalPedidos() { return itensPedido.size(); }
}
