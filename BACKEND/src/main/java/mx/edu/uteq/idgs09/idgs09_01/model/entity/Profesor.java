package mx.edu.uteq.idgs09.idgs09_01.model.entity;

import lombok.Data;

@Data
public class Profesor {
    private int id;
    private String nombre;
    private String apellidos;
    private String genero;
    private String clavePe; // campo que se actualizará al asignar PE
    private boolean activo;
}
