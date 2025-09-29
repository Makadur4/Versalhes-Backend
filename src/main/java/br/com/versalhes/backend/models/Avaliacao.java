package br.com.versalhes.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Perfume_Id")
    private Perfume perfume;

    @ManyToOne
    @JoinColumn(name = "Cliente_Id")
    private Cliente cliente;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Avaliacao")
    private int avaliacao;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Perfume getPerfume() { return perfume; }
    public void setPerfume(Perfume perfume) { this.perfume = perfume; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getAvaliacao() { return avaliacao; }
    public void setAvaliacao(int avaliacao) { this.avaliacao = avaliacao; }
}
