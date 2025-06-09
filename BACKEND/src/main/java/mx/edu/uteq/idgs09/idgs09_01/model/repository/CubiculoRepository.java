package mx.edu.uteq.idgs09.idgs09_01.model.repository;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Cubiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CubiculoRepository extends JpaRepository<Cubiculo, Long> {
    List<Cubiculo> findByActivo(boolean activo);
}