package br.senac.loja.repositorios;

import br.senac.loja.entidades.Cliente;
import br.senac.loja.entidades.Fornecedor;
import br.senac.loja.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio
        extends JpaRepository<Cliente, Long> {}
