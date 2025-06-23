package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "programa_educativo_profesor")
@Data
public class ProgramaEducativoProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_pro", nullable = false)
    private int id_pro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pe")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProgramaEducativo programaEducativo;
}

