package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pe")
    private int id_pe;
    private String programaEducativo;
    private String clave;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_division")
    @JsonIgnoreProperties(value = {"programasEducativos"})
    private Division division;

    @Column(name = "id_pro")
    private Integer idPro; // Solo el ID del profesor externo
}
