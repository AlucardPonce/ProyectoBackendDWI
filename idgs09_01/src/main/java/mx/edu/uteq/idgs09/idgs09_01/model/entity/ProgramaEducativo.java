package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class ProgramaEducativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pe;
    private String programaEducativo;
    private String clave;
    private boolean activo;
    @ManyToOne
    @JoinColumn(name = "id_division")
    @JsonIgnoreProperties(value = {"programasEducativos"})
    private Division division;

}
