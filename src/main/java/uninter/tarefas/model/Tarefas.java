package uninter.tarefas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tarefas_model")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEntrega;
    private String responsavel;
}
