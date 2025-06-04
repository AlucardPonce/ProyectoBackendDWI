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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/pe")
public class ProgramaEducativoController {
    @Autowired
    private ProgramaEducativoRepo repo;
    @Autowired
    private DivisionRepo drepo;

    @PostMapping()
    public ResponseEntity<ProgramaEducativo> crear(@RequestBody ProgramaEducativo p) {
        ProgramaEducativo entity = repo.save(p);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody ProgramaEducativo entity) {
        Optional<ProgramaEducativo> opt = repo.findById(id);
        if (opt.isPresent()) {
            ProgramaEducativo p = opt.get();
            p.setProgramaEducativo(entity.getProgramaEducativo());
            p.setClave(entity.getClave());
            p.setActivo(entity.isActivo());
            p.setDivision(entity.getDivision());
            return ResponseEntity.ok(repo.save(p));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        Optional<ProgramaEducativo> opt = repo.findById(id);
        if (opt.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public List<ProgramaEducativo> buscarTodos(@RequestParam(required = false, defaultValue = "false") boolean soloActivos){
        if(soloActivos){
            return repo.findAll().stream().filter(ProgramaEducativo::isActivo).toList();
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