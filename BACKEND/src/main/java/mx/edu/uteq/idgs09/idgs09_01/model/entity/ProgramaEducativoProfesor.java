package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "programa_educativo_profesor")
public class ProgramaEducativoProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_pro", nullable = false)
    private int id_pro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pe")
    private ProgramaEducativo programaEducativo;
}

