package mx.edu.uteq.idgs09.idgs09_01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;

public interface ProgramaEducativoProfesorRepo extends JpaRepository<ProgramaEducativoProfesor, Integer> {
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para buscar por el ID del programa educativo o del profesor
    // public List<ProgramaEducativoProfesor> findByIdProgramaEducativo(int idProgramaEducativo);
    // public List<ProgramaEducativoProfesor> findByIdProfesor(int idProfesor);
    
}
