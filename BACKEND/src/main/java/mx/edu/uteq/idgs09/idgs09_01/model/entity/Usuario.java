package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean activo = true;
    
    public enum Rol {
        ALUMNO, MAESTRO, ADMIN
    }
}