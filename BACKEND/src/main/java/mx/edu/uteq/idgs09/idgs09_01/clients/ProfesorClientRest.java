package mx.edu.uteq.idgs09.idgs09_01.clients;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Profesor;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "profesor-service", url = "http://localhost:8081")
public interface ProfesorClientRest {

    @GetMapping("/api/profesores/{id}")
    Profesor porId(@PathVariable("id") int id);

    @PutMapping("/api/profesores/{id}")
    Profesor editarProfesor(@PathVariable("id") int id, @RequestBody Profesor profesor);

    default Profesor obtenerProfesorDePrograma(ProgramaEducativo pe) {
        if (pe.getIdPro() != null) {
            return porId(pe.getIdPro());
        }
        return null;
    }
}
