
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api/division")
public class DivisionController {
    @Autowired
    private DivisionRepo repo;

        @PostMapping()
    public ResponseEntity<Division> crear(@RequestBody Division d) {
        Division entity = repo.save(d);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody Division entity) {
        Optional<Division> opt = repo.findById(id);
        if (opt.isPresent()) {
            Division d = opt.get();
            d.setNombre(entity.getNombre());
            d.setClave(entity.getClave());
            d.setActivo(entity.isActivo());
            return ResponseEntity.ok(repo.save(d));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        Optional<Division> opt = repo.findById(id);
        if (opt.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public List<Division> buscarTodos(@RequestParam boolean soloActivos){
        if(soloActivos){
            return repo.findAll().stream().filter(Division::isActivo).toList();
        }
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}