package mx.edu.uteq.idgs09.idgs09_01.model.repository;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioIdAndLeidaFalse(Long usuarioId);
    List<Notificacion> findByUsuarioId(Long usuarioId);
}