package mx.edu.uteq.idgs09.idgs09_01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;

public interface ProgramaEducativoProfesorRepo extends JpaRepository<ProgramaEducativoProfesor, Integer> {
    
}
