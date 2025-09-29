package br.com.versalhes.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ItemPedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Pedido_Id")
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "Perfume_Id")
    private Perfume perfume;

    @Column(name = "Quantidade")
    private short quantidade;

    @Column(name = "ValorUnitario")
    private double valorUnitario;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Perfume getPerfume() { return perfume; }
    public void setPerfume(Perfume perfume) { this.perfume = perfume; }

    public short getQuantidade() { return quantidade; }
    public void setQuantidade(short quantidade) { this.quantidade = quantidade; }

    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }
}
