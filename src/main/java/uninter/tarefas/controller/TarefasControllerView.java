package uninter.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uninter.tarefas.model.Tarefas;
import uninter.tarefas.service.TarefasService;

import java.util.List;

@Controller
@RequestMapping("/")
public class TarefasControllerView {

    @Autowired
    private TarefasService tarefasService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("tarefas", tarefasService.listarTodas() != null ? tarefasService.listarTodas() : List.of());
        return "tarefas/lista";
    }

    @GetMapping("/tarefas-view")
    public String listar(Model model) {
        model.addAttribute("tarefas", tarefasService.listarTodas());
        return "tarefas/lista";
    }

    @GetMapping("/nova")
    public String novaTarefaForm(Model model) {
        model.addAttribute("tarefa", new Tarefas());
        return "tarefas/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Tarefas tarefa) {
        tarefasService.salvar(tarefa);
        return "redirect:/tarefas-view";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Tarefas tarefa = tarefasService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada: " + id));
        model.addAttribute("tarefa", tarefa);
        return "tarefas/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        tarefasService.deletar(id);
        return "redirect:/tarefas-view";
    }
}
