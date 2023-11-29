package br.senac.loja.controladores;

import br.senac.loja.entidades.Produto;
import br.senac.loja.repositorios.ProdutoRepositorio;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoControlador {

    private ProdutoRepositorio produtoRepositorio;

    public ProdutoControlador(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }


    // GET: Obter todos os produtos com paginação
    @GetMapping
    public ResponseEntity<Page<Produto>> listarProdutos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina) {

        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        Page<Produto> produtos = produtoRepositorio.findAll(pageable);

        return ResponseEntity.ok(produtos);
    }

    // GET: Obter um único produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepositorio.findById(id);
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar um novo produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody @Valid Produto produto) {
        Produto produtoCriado = produtoRepositorio.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    // PUT: Atualizar um produto existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid Produto produtoAtualizado) {
        if (!produtoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoAtualizado.setId(id);
        Produto produtoAtualizadoSalvo = produtoRepositorio.save(produtoAtualizado);
        return ResponseEntity.ok(produtoAtualizadoSalvo);
    }

    // DELETE: Excluir um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        if (!produtoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
