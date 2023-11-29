package br.senac.loja.repositorios;

import br.senac.loja.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepositorio
        extends JpaRepository<Funcionario, Long> {}
