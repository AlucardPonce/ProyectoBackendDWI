package mx.edu.uteq.idgs09.idgs09_01.controller;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Cubiculo;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.CubiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cubiculos")
public class CubiculoController {
    
    @Autowired
    private CubiculoRepository cubiculoRepository;
    
    @GetMapping
    public ResponseEntity<List<Cubiculo>> obtenerCubiculos(@RequestParam(required = false) boolean activos) {
        List<Cubiculo> cubiculos;
        if (activos) {
            cubiculos = cubiculoRepository.findByActivo(true);
        } else {
            cubiculos = cubiculoRepository.findAll();
        }
        return ResponseEntity.ok(cubiculos);
    }
    
    @PostMapping
    public ResponseEntity<Cubiculo> crearCubiculo(@RequestBody Cubiculo cubiculo) {
        Cubiculo nuevoCubiculo = cubiculoRepository.save(cubiculo);
        return ResponseEntity.ok(nuevoCubiculo);
    }
    
    @PutMapping("/{id}/estado")
    public ResponseEntity<Cubiculo> cambiarEstado(
            @PathVariable Long id,
            @RequestParam boolean activo) {
        
        return cubiculoRepository.findById(id)
                .map(cubiculo -> {
                    cubiculo.setActivo(activo);
                    Cubiculo actualizado = cubiculoRepository.save(cubiculo);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}