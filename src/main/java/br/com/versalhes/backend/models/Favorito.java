package br.com.versalhes.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Favorito")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Perfume_Id")
    @JsonIgnore
    private Perfume perfume;

    @ManyToOne
    @JoinColumn(name = "Cliente_Id")
    @JsonIgnore
    private Cliente cliente;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Perfume getPerfume() { return perfume; }
    public void setPerfume(Perfume perfume) { this.perfume = perfume; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
