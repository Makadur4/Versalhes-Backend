package br.com.versalhes.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DadosPagamento")
public class DadosPagamento {
    @Id
    @Column(name = "Pedido_Id")
    private long pedidoId;

    @Column(name = "BandeiraCartao")
    private String bandeiraCartao;

    @Column(name = "NumeroCartao")
    private String numeroCartao;

    @Column(name = "DataValidade")
    private String dataValidade;

    @Column(name = "QuantidadeParcelas")
    private short quantidadeParcelas;

    public long getPedidoId() { return pedidoId; }
    public void setPedidoId(long pedidoId) { this.pedidoId = pedidoId; }

    public String getBandeiraCartao() { return bandeiraCartao; }
    public void setBandeiraCartao(String bandeiraCartao) { this.bandeiraCartao = bandeiraCartao; }

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public String getDataValidade() { return dataValidade; }
    public void setDataValidade(String dataValidade) { this.dataValidade = dataValidade; }

    public short getQuantidadeParcelas() { return quantidadeParcelas; }
    public void setQuantidadeParcelas(short quantidadeParcelas) { this.quantidadeParcelas = quantidadeParcelas; }
}
