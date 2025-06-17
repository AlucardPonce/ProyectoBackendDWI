package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import lombok.Data;

@Data
public class Profesor {
    private int id;
    private String nombre;
    private String apellidos;
    private String genero;
    private String id_pe;
    private boolean activo;
}