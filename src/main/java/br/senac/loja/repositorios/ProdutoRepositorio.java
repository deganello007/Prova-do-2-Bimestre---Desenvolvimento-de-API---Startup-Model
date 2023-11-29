package br.senac.loja.repositorios;

import br.senac.loja.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositorio
        extends JpaRepository<Produto, Long> {

    List<Produto> searchByNome(String nome);

    Produto findByNome(String nome);

    Produto findByDescricao(String descricao);

    @Query("select c from Produto c where lower(c.nome) like lower(concat(:termo, '%')) or lower(c.descricao) like lower(concat(:termo, '%'))")
    List<Produto> searchByNomeEDescricao(@Param("termo") String termoBusca);
}
