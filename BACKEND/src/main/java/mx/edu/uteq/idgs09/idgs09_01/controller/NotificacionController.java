package mx.edu.uteq.idgs09.idgs09_01.controller;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Notificacion;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    
    @Autowired
    private NotificacionRepository notificacionRepository;
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorUsuario(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) boolean noLeidas) {
        
        List<Notificacion> notificaciones;
        if (noLeidas) {
            notificaciones = notificacionRepository.findByUsuarioIdAndLeidaFalse(usuarioId);
        } else {
            notificaciones = notificacionRepository.findByUsuarioId(usuarioId);
        }
        return ResponseEntity.ok(notificaciones);
    }
    
    @PutMapping("/{id}/marcar-leida")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        return notificacionRepository.findById(id)
                .map(notificacion -> {
                    notificacion.setLeida(true);
                    Notificacion actualizada = notificacionRepository.save(notificacion);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}