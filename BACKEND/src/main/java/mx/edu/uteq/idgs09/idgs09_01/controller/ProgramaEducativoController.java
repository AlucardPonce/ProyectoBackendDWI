package mx.edu.uteq.idgs09.idgs09_01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.services.ProgramaEducativoService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pe")
public class ProgramaEducativoController {

    @Autowired
    private ProgramaEducativoService serv;

    @PostMapping()
    public ResponseEntity<ProgramaEducativo> crear(@RequestBody ProgramaEducativo pe) {
        ProgramaEducativo entity = serv.crear(pe);
        if (entity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<List<ProgramaEducativo>> buscarTodos(
            @RequestParam(defaultValue = "false") boolean soloActivos) {
        List<ProgramaEducativo> programas = serv.buscar(soloActivos);
        return ResponseEntity.ok(programas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaEducativo> buscarPorId(@PathVariable int id) {
        return serv.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaEducativo> editar(@PathVariable int id, @RequestBody ProgramaEducativo entity) {
        Optional<ProgramaEducativo> opt = serv.editar(id, entity);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (serv.borrar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/division/{divisionId}")
    public ResponseEntity<List<ProgramaEducativo>> buscarPorDivision(
            @PathVariable int divisionId,
            @RequestParam(defaultValue = "false") boolean soloActivos) {
        
        List<ProgramaEducativo> programas = serv.buscarPorDivision(divisionId, soloActivos);
        return ResponseEntity.ok(programas);
    }
}