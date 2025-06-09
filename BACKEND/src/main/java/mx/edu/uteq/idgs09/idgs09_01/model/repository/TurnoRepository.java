package mx.edu.uteq.idgs09.idgs09_01.model.repository;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByAlumnoId(Long alumnoId);
    List<Turno> findByMaestroId(Long maestroId);
    List<Turno> findByEstado(Turno.Estado estado);
}