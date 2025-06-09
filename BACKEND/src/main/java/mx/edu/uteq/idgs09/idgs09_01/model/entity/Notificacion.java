package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "Notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String mensaje;
    
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date fechaEnvio;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean leida = false;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_turno")
    private Turno turno;
}