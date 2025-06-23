package mx.edu.uteq.idgs09.idgs09_01.clients;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Profesor;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "profesor-service", url = "http://localhost:8081")
public interface ProfesorClientRest {

    @GetMapping("/api/profesores/{id}")
    Profesor porId(@PathVariable("id") int id);

    @PutMapping("/api/profesores/editarpro/{id}")
    Profesor editarpro(@PathVariable("id") int id, @RequestBody Profesor p);

    default Profesor obtenerProfesorDePrograma(ProgramaEducativoProfesor pep) {
        if (pep.getId_pro() != 0) {
            return porId(pep.getId_pro());
        }
        return null;
    }
}
