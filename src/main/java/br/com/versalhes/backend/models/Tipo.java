package br.com.versalhes.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Tipo")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Nome")
    private String nome;

    @OneToMany(mappedBy = "tipo")
    @JsonIgnore
    private List<Perfume> perfumes;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String tipo) {
        this.nome = tipo;
    }

    public List<Perfume> getPerfumes() { return perfumes; }
    public void setPerfumes(List<Perfume> perfumes) { this.perfumes = perfumes; }
}
