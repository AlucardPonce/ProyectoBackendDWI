package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Cubiculo")
public class Cubiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String ubicacion;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean activo = true;
}