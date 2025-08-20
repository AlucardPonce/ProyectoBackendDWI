package mx.edu.uteq.idgs09.idgs09_01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;

@Repository
public interface ProgramaEducativoRepo extends JpaRepository<ProgramaEducativo, Integer> {
    // Mantener solo el método que ya tenías
    public ProgramaEducativo findByClave(String clave);
}