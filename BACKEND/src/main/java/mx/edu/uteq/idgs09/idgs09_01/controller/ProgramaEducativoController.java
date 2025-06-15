package mx.edu.uteq.idgs09.idgs09_01.controller;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;

import java.util.Collections;
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
import mx.edu.uteq.idgs09.idgs09_01.services.ProgramaEducativoService;
import org.springframework.http.HttpStatus;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Profesor;
import org.springframework.cloud.openfeign.FeignClient;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/pe")
public class ProgramaEducativoController {
    @Autowired
    private DivisionRepo drepo;
    @Autowired
    private ProgramaEducativoService serv;

    @GetMapping
    public List<ProgramaEducativo> buscarTodos(@RequestParam boolean soloActivos) {
        if (soloActivos) {
            return serv.findAll().stream().filter(ProgramaEducativo::isActivo).toList();
        }
        return serv.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return serv.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestParam int idDivision, @RequestBody ProgramaEducativo pe) {
        Optional<Division> opt = drepo.findById(idDivision);
        if (opt.isPresent()) {
            Division d = opt.get();
            pe.setDivision(d);
            return ResponseEntity.ok(serv.save(pe));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody ProgramaEducativo pe) {
        Optional<ProgramaEducativo> opt = serv.findById(id);
        if (opt.isPresent()) {
            ProgramaEducativo p = opt.get();
            Optional<Division> divOpt = drepo.findById(pe.getDivision().getId());
            if (divOpt.isPresent()) {
                p.setClave(pe.getClave());
                p.setProgramaEducativo(pe.getProgramaEducativo());
                p.setActivo(pe.isActivo());
                p.setDivision(divOpt.get());
                return ResponseEntity.ok(serv.save(p));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Division no encontrada");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable int id) {
        Optional<ProgramaEducativo> opt = serv.findById(id);
        if (opt.isPresent()) {
            serv.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-profesor/{peId}")
    public ResponseEntity<?> asignarProfesor(@PathVariable Long peId, @RequestBody Profesor profesor) {
        Optional<Profesor> opt;
        try {
            opt = serv.asignarProfesor(profesor, peId);
        } catch (FeignException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje", 
            "No existe el profesor por el id o error en la comunicación" + e.getMessage() ));
        }
        if (opt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
        }
        return ResponseEntity.notFound().build();
}

}