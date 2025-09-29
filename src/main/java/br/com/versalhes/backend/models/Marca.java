package br.com.versalhes.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Nome")
    private String nome;

    @OneToMany(mappedBy = "marca")
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
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Perfume> getPerfumes() { return perfumes; }
    public void setPerfumes(List<Perfume> perfumes) { this.perfumes = perfumes; }
}
