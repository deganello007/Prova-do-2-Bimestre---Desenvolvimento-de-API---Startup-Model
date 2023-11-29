package br.senac.loja.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Evento {
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
    private String quantidadePessoas;

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

    public String getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(String quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
}
