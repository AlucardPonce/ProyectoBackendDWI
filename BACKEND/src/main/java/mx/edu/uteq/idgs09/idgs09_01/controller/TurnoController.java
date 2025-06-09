package mx.edu.uteq.idgs09.idgs09_01.controller;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Turno;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Usuario;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.TurnoRepository;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
    
    @Autowired
    private TurnoRepository turnoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        // Generar folio único
        turno.setFolio("TURN-" + UUID.randomUUID().toString().substring(0, 8));
        Turno nuevoTurno = turnoRepository.save(turno);
        return ResponseEntity.ok(nuevoTurno);
    }
    
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Turno>> obtenerTurnosPorAlumno(@PathVariable Long alumnoId) {
        List<Turno> turnos = turnoRepository.findByAlumnoId(alumnoId);
        return ResponseEntity.ok(turnos);
    }
    
    @GetMapping("/maestro/{maestroId}")
    public ResponseEntity<List<Turno>> obtenerTurnosPorMaestro(@PathVariable Long maestroId) {
        List<Turno> turnos = turnoRepository.findByMaestroId(maestroId);
        return ResponseEntity.ok(turnos);
    }
    
    @PutMapping("/{id}/estado")
    public ResponseEntity<Turno> actualizarEstado(
            @PathVariable Long id,
            @RequestParam Turno.Estado estado) {
        
        Optional<Turno> turnoOpt = turnoRepository.findById(id);
        if (turnoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Turno turno = turnoOpt.get();
        turno.setEstado(estado);
        Turno turnoActualizado = turnoRepository.save(turno);
        return ResponseEntity.ok(turnoActualizado);
    }
    
    @PutMapping("/{id}/cubiculo")
    public ResponseEntity<Turno> asignarCubiculo(
            @PathVariable Long id,
            @RequestParam Long cubiculoId) {
        
        // Implementar lógica para asignar cubículo
        // Esto requeriría un CubiculoRepository y lógica adicional
        return ResponseEntity.ok().build();
    }
}