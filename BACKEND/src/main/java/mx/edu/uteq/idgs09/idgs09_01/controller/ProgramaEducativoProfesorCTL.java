package mx.edu.uteq.idgs09.idgs09_01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoProfesorRepo;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/pep")
public class ProgramaEducativoProfesorCTL {
    @Autowired
    private ProgramaEducativoProfesorRepo repo;
    
    @GetMapping()
    public List<ProgramaEducativoProfesor> buscarTodos() {
        return repo.findAll();
    }
    


}
