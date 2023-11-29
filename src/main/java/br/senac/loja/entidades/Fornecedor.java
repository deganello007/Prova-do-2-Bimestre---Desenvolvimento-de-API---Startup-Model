package br.senac.loja.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String nome;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String telefone;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String produtoOferecido;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String qualidade;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String tempoEntrega;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProdutoOferecido() {
        return produtoOferecido;
    }

    public void setProdutoOferecido(String produtoOferecido) {
        this.produtoOferecido = produtoOferecido;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(String tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }
}
