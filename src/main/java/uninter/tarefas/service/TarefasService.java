package uninter.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uninter.tarefas.model.Tarefas;
import uninter.tarefas.repository.TarefasRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefasService {

    @Autowired
    private TarefasRepository tarefasRepository;

    public Tarefas salvar(Tarefas tarefa) {
        return tarefasRepository.save(tarefa);
    }

    public List<Tarefas> listarTodas() {
        return tarefasRepository.findAll();
    }


    public Optional<Tarefas> buscarPorId(Long id) {
        return tarefasRepository.findById(id);
    }

    public Tarefas atualizar(Long id, Tarefas tarefaAtualizada) {
        return tarefasRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setNome(tarefaAtualizada.getNome());
                    tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());
                    tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
                    return tarefasRepository.save(tarefa);
                }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void deletar(Long id) {
        if (!tarefasRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        tarefasRepository.deleteById(id);
    }
}

