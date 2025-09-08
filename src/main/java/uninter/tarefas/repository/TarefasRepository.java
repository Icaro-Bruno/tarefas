package uninter.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uninter.tarefas.model.Tarefas;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

}
