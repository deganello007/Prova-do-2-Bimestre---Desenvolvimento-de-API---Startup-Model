package br.senac.loja.controladores;

import br.senac.loja.entidades.Evento;
import br.senac.loja.repositorios.EventoRepositorio;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {

    private EventoRepositorio eventoRepositorio;

    public EventoControlador(EventoRepositorio eventoRepositorio) {
        this.eventoRepositorio = eventoRepositorio;
    }


    // GET: Obter todos os eventos com paginação
    @GetMapping
    public ResponseEntity<Page<Evento>> listarEventos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina) {

        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        Page<Evento> eventos = eventoRepositorio.findAll(pageable);

        return ResponseEntity.ok(eventos);
    }

    // GET: Obter um único evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obterEventoPorId(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepositorio.findById(id);
        return evento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar um novo evento
    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody @Valid Evento evento) {
        Evento eventoCriado = eventoRepositorio.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoCriado);
    }

    // PUT: Atualizar um evento existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody @Valid Evento eventoAtualizado) {
        if (!eventoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventoAtualizado.setId(id);
        Evento eventoAtualizadoSalvo = eventoRepositorio.save(eventoAtualizado);
        return ResponseEntity.ok(eventoAtualizadoSalvo);
    }

    // DELETE: Excluir um evento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEvento(@PathVariable Long id) {
        if (!eventoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

