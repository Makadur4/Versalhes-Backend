package br.com.versalhes.gerente.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Tipo")
    private long idTipo;

    @Column(name = "Tipo")
    private String tipo;

    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
