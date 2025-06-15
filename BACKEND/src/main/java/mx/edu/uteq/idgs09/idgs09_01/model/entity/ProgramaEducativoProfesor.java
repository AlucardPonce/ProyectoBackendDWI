package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "programa_educativo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaEducativoProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "profesor_id", nullable = false)
    private int profesorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pe")
    private ProgramaEducativo programaEducativo;
}

