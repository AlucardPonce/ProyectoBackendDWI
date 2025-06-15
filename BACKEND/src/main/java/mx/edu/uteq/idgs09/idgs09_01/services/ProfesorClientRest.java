package mx.edu.uteq.idgs09.idgs09_01.services;
import java.util.List;

@FeignClient(name = "idgs09-02", url = "localhost:8081")
public class ProfesorClientRest {
    interface ProfesorClientRest {
    
        @GetMapping("/profesores")
        List<Profesor> getProfesores();
        
        @PutMapping("/api/profesor/{id}")
        Profesor editarProfesor(@PathVariable int id, @RequestBody Profesor p);
        
    }
    
}
