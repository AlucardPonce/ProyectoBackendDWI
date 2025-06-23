package mx.edu.uteq.idgs09.idgs09_01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs09.idgs09_01.clients.ProfesorClientRest;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Division;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Profesor;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoProfesorRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/pep")
public class ProgramaEducativoProfesorCTL {
    @Autowired
    private ProgramaEducativoProfesorRepo repo;

    @Autowired
    private ProfesorClientRest profesorClientRest;

    @GetMapping()
    public List<ProgramaEducativoProfesor> buscarTodos() {
        return repo.findAll();
    }

    @PutMapping("/asignarpro/{id}")
    public ResponseEntity<?> asignarPro(@PathVariable int id, @RequestBody ProgramaEducativoProfesor entity) {
        Optional<ProgramaEducativoProfesor> opt = repo.findById(id);
        if (opt.isPresent()) {
            ProgramaEducativoProfesor pep = opt.get();
            pep.setId_pro(entity.getId_pro());
            repo.save(pep);

            // Llama al microservicio de profesor para actualizar el id_pe
            if (pep.getId_pro() != 0 && pep.getProgramaEducativo() != null) {
                Profesor profesor = profesorClientRest.porId(pep.getId_pro());
                profesor.setId_pe(String.valueOf(pep.getProgramaEducativo().getId_pe()));
                profesorClientRest.editarpro(pep.getId_pro(), profesor);
            }

            return ResponseEntity.ok(pep);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
