package mx.edu.uteq.idgs09.idgs09_01.controller;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.Usuario;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isEmpty() || !usuarioOpt.get().getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        
        Usuario usuario = usuarioOpt.get();
        return ResponseEntity.ok(usuario);
    }
}