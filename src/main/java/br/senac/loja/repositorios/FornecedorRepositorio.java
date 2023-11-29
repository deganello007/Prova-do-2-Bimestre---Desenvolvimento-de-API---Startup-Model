package br.senac.loja.repositorios;

import br.senac.loja.entidades.Fornecedor;
import br.senac.loja.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepositorio
        extends JpaRepository<Fornecedor, Long> {}
