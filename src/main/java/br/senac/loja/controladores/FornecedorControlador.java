package br.senac.loja.controladores;

import br.senac.loja.entidades.Fornecedor;
import br.senac.loja.repositorios.FornecedorRepositorio;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorControlador {

    private FornecedorRepositorio fornecedorRepositorio;

    public FornecedorControlador(FornecedorRepositorio fornecedorRepositorio) {
        this.fornecedorRepositorio = fornecedorRepositorio;
    }


    // GET: Obter todos os fornecedores com paginação
    @GetMapping
    public ResponseEntity<Page<Fornecedor>> listarFornecedores(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina) {

        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        Page<Fornecedor> fornecedores = fornecedorRepositorio.findAll(pageable);

        return ResponseEntity.ok(fornecedores);
    }

    // GET: Obter um único fornecedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> obterFornecedorPorId(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepositorio.findById(id);
        return fornecedor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar um novo fornecedor
    @PostMapping
    public ResponseEntity<Fornecedor> criarFornecedor(@RequestBody @Valid Fornecedor fornecedor) {
        Fornecedor fornecedorCriado = fornecedorRepositorio.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorCriado);
    }

    // PUT: Atualizar um fornecedor existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid Fornecedor fornecedorAtualizado) {
        if (!fornecedorRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fornecedorAtualizado.setId(id);
        Fornecedor fornecedorAtualizadoSalvo = fornecedorRepositorio.save(fornecedorAtualizado);
        return ResponseEntity.ok(fornecedorAtualizadoSalvo);
    }

    // DELETE: Excluir um fornecedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFornecedor(@PathVariable Long id) {
        if (!fornecedorRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fornecedorRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
