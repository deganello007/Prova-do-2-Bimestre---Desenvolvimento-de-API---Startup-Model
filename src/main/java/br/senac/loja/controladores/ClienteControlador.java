package br.senac.loja.controladores;

import br.senac.loja.entidades.Cliente;
import br.senac.loja.repositorios.ClienteRepositorio;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    private ClienteRepositorio clienteRepositorio;

    public ClienteControlador(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }


    // GET: Obter todos os clientes com paginação
    @GetMapping
    public ResponseEntity<Page<Cliente>> listarClientes(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina) {

        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        Page<Cliente> clientes = clienteRepositorio.findAll(pageable);

        return ResponseEntity.ok(clientes);
    }

    // GET: Obter um único cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
        Cliente clienteCriado = clienteRepositorio.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    // PUT: Atualizar um cliente existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado) {
        if (!clienteRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteAtualizado.setId(id);
        Cliente clienteAtualizadoSalvo = clienteRepositorio.save(clienteAtualizado);
        return ResponseEntity.ok(clienteAtualizadoSalvo);
    }

    // DELETE: Excluir um cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        if (!clienteRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

