package br.com.versalhes.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Cliente_Id")
    private Cliente cliente;

    @Column(name = "DataPedido")
    private LocalDate dataPedido;

    @Column(name = "ValorProdutos")
    private double valorProdutos;

    @Column(name = "PrazoFrete")
    private short prazoFrete;

    @Column(name = "ValorFrete")
    private double valorFrete;

    @Column(name = "DataEntrega")
    private LocalDate dataEntrega;

    @Column(name = "ValorAcrescimo")
    private double valorAcrescimo;

    @Column(name = "ValorTotal")
    private double valorTotal;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private EnderecoEntrega enderecoEntrega;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private DadosPagamento dadosPagamento;

    @Column(name = "Status_Id")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDate getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }

    public double getValorProdutos() { return valorProdutos; }
    public void setValorProdutos(double valorProdutos) { this.valorProdutos = valorProdutos; }

    public short getPrazoFrete() { return prazoFrete; }
    public void setPrazoFrete(short prazoFrete) { this.prazoFrete = prazoFrete; }

    public double getValorFrete() { return valorFrete; }
    public void setValorFrete(double valorFrete) { this.valorFrete = valorFrete; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public double getValorAcrescimo() { return valorAcrescimo; }
    public void setValorAcrescimo(double valorAcrescimo) { this.valorAcrescimo = valorAcrescimo; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public EnderecoEntrega getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public DadosPagamento getDadosPagamento() { return dadosPagamento; }
    public void setDadosPagamento(DadosPagamento dadosPagamento) { this.dadosPagamento = dadosPagamento; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public List<ItemPedido> getItensPedido() { return itensPedido; }
    public void setItensPedido(List<ItemPedido> itensPedido) { this.itensPedido = itensPedido; }

    @Converter(autoApply = true)
    public static class StatusConverter implements AttributeConverter<Status, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Status status) {
            if (status == null) return null;
            return status.getValor();
        }

        @Override
        public Status convertToEntityAttribute(Integer valor) {
            if (valor == null) return null;

            for (Status status : Status.values()) {
                if (status.getValor() == valor) {
                    return status;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
