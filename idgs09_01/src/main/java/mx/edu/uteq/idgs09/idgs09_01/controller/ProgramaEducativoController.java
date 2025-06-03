package mx.edu.uteq.idgs09.idgs09_01.controller;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Division;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.DivisionRepo;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/pe")
public class ProgramaEducativoController {
    @Autowired
    private ProgramaEducativoRepo repo;
    @Autowired
    private DivisionRepo drepo;

    @GetMapping()
    public List<ProgramaEducativo> buscarTodos(@RequestParam(required = false) Boolean soloActivos,
            @RequestParam(required = false) Boolean soloInactivos) {
        if (Boolean.TRUE.equals(soloActivos)) {
            return repo.findAll().stream().filter(ProgramaEducativo::isActivo).toList();
        }
        if (Boolean.TRUE.equals(soloInactivos)) {
            return repo.findAll().stream().filter(pe -> !pe.isActivo()).toList();
        }
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestParam int idDivision, @RequestBody ProgramaEducativo pe) {
        Optional<Division> opt = drepo.findById(idDivision);
        if (opt.isPresent()) {
            Division division = opt.get();
            pe.setDivision(division);
            ProgramaEducativo saved = repo.save(pe);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable int id,
            @RequestParam(required = false) Integer idDivision,
            @RequestBody ProgramaEducativo pe) {

        Optional<ProgramaEducativo> opt = repo.findById(id);
        if (opt.isPresent()) {
            ProgramaEducativo existente = opt.get();
            existente.setProgramaEducativo(pe.getProgramaEducativo());
            existente.setClave(pe.getClave());
            existente.setActivo(pe.isActivo());

            if (idDivision != null) {
                Optional<Division> divOpt = drepo.findById(idDivision);
                if (divOpt.isPresent()) {
                    existente.setDivision(divOpt.get());
                } else {
                    return ResponseEntity.badRequest().body("División no encontrada");
                }
            }

            ProgramaEducativo actualizado = repo.save(existente);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

}

/*
 @PutMapping("/{id}") // Expone el método como endpoint PUT en /api/pe/{id}
public ResponseEntity<?> editar(
        @PathVariable int id, // Obtiene el id del programa educativo de la URL
        @RequestParam(required = false) Integer idDivision, // Recibe opcionalmente el id de la división como parámetro
        @RequestBody ProgramaEducativo pe) { // Recibe el objeto ProgramaEducativo con los datos a actualizar en el body

    Optional<ProgramaEducativo> opt = repo.findById(id); // Busca el programa educativo por id en la base de datos
    if (opt.isPresent()) { // Si existe el programa educativo...
        ProgramaEducativo existente = opt.get(); // Obtiene el objeto existente
        existente.setProgramaEducativo(pe.getProgramaEducativo()); // Actualiza el nombre del programa educativo
        existente.setClave(pe.getClave()); // Actualiza la clave
        existente.setActivo(pe.isActivo()); // Actualiza el estado activo/inactivo

        if (idDivision != null) { // Si se envió un idDivision...
            Optional<Division> divOpt = drepo.findById(idDivision); // Busca la división por id
            if (divOpt.isPresent()) { // Si existe la división...
                existente.setDivision(divOpt.get()); // Asigna la nueva división al programa educativo
            } else {
                return ResponseEntity.badRequest().body("División no encontrada"); // Si no existe, responde con error 400
            }
        }

        ProgramaEducativo actualizado = repo.save(existente); // Guarda los cambios en la base de datos
        return ResponseEntity.ok(actualizado); // Devuelve el objeto actualizado y status 200 OK
    }
    return ResponseEntity.notFound().build(); // Si no existe el programa educativo, responde con 404 Not Found
}
 */