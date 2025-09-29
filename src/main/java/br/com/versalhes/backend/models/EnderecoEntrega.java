package br.com.versalhes.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EnderecoEntrega")
public class EnderecoEntrega {
    @Id
    @Column(name = "Pedido_Id")
    private long pedidoId;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "Numero")
    private String numero;

    @Column(name = "Complemento")
    private String complemento;

    @Column(name = "Bairro")
    private String bairro;

    @Column(name = "Municipio")
    private String municipio;

    @Column(name = "UF")
    private String uf;

    public long getPedidoId() { return pedidoId; }
    public void setPedidoId(long pedidoId) { this.pedidoId = pedidoId; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
}
