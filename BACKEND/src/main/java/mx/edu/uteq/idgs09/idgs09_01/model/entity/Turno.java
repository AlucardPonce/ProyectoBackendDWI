package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "Turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String folio;
    
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreacion;
    
    @Column(nullable = false)
    private Date fechaAsignado;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;
    
    private String motivo;
    
    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false)
    private Usuario alumno;
    
    @ManyToOne
    @JoinColumn(name = "id_maestro", nullable = false)
    private Usuario maestro;
    
    @ManyToOne
    @JoinColumn(name = "id_cubiculo")
    private Cubiculo cubiculo;
    
    public enum Estado {
        PENDIENTE, ACEPTADO, RECHAZADO, CANCELADO, COMPLETADO
    }
}