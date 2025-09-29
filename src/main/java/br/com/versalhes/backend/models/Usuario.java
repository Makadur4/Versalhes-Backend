package br.com.versalhes.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario" )
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Login")
    private String login;

    @Column(name = "Senha")
    private String senha;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
