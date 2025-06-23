package mx.edu.uteq.idgs09.idgs09_01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs09.idgs09_01.services.DivisionService;

import java.util.List;
import java.util.Optional;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Division;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.DivisionRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/division")
public class DivisionController {
    @Autowired
    private DivisionService serv;

    @PostMapping()
    public ResponseEntity<Division> crear(@RequestBody Division d) {
        Division entity = serv.crear(d);
        if (entity != null) {
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody Division entity) {
        Optional<Division> opt = serv.editar(id, entity);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (serv.borrar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public List<Division> buscarTodos(@RequestParam boolean soloActivos) {
        return serv.buscar(soloActivos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return serv.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}