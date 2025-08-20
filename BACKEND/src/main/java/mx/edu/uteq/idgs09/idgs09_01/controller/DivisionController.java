package mx.edu.uteq.idgs09.idgs09_01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import mx.edu.uteq.idgs09.idgs09_01.services.DivisionService;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Division;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/division")
public class DivisionController {
    
    @Autowired
    private DivisionService serv;

    // Crear nueva división
    @PostMapping()
    public ResponseEntity<Division> crear(@RequestBody Division d) {
        Division entity = serv.crear(d);
        if (entity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        }
        return ResponseEntity.badRequest().build();
    }

    // Obtener todas las divisiones
    @GetMapping()
    public ResponseEntity<List<Division>> buscarTodos(
            @RequestParam(defaultValue = "false") boolean soloActivos) {
        List<Division> divisiones = serv.buscar(soloActivos);
        return ResponseEntity.ok(divisiones);
    }

    // Obtener división por ID
    @GetMapping("/{id}")
    public ResponseEntity<Division> buscarPorId(@PathVariable int id) {
        return serv.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar división
    @PutMapping("/{id}")
    public ResponseEntity<Division> editar(@PathVariable int id, @RequestBody Division entity) {
        Optional<Division> opt = serv.editar(id, entity);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar división (borrado físico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (serv.borrar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Desactivar división (borrado lógico - endpoint adicional)
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable int id) {
        if (serv.borrarLogico(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}