package uninter.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uninter.tarefas.model.Tarefas;
import uninter.tarefas.service.TarefasService;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<Tarefas> criar(@RequestBody Tarefas tarefa) {
        Tarefas novaTarefa = tarefasService.salvar(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    @GetMapping
    public ResponseEntity<List<Tarefas>> listarTodas() {
        return ResponseEntity.ok(tarefasService.listarTodas());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Tarefas> buscarPorId(@PathVariable Long id) {
        return tarefasService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> atualizar(@PathVariable Long id, @RequestBody Tarefas tarefa) {
        try {
            Tarefas tarefaAtualizada = tarefasService.atualizar(id, tarefa);
            return ResponseEntity.ok(tarefaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            tarefasService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
