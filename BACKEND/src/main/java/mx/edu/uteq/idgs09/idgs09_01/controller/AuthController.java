package mx.edu.uteq.idgs09.idgs09_01.controller;

import mx.edu.uteq.idgs09.idgs09_01.model.dto.LoginRequest;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Usuario;
import mx.edu.uteq.idgs09.idgs09_01.services.AuthService;
import mx.edu.uteq.idgs09.idgs09_01.util.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SecurityLogger securityLogger;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean success = authService.authenticate(
                    loginRequest.getUsername(),
                    loginRequest.getPassword());

            if (success) {
                securityLogger.loginSuccess(loginRequest.getUsername());
                return ResponseEntity.ok("Login successful");
            } else {
                securityLogger.loginFailure(loginRequest.getUsername(), "Invalid credentials");
                return ResponseEntity.status(401).body("Unauthorized");
            }
        } catch (Exception e) {
            securityLogger.exceptionCaught("/api/auth/login", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest registerRequest) {
        try {
            Usuario user = authService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
            return ResponseEntity.ok("User registered with ID: " + user.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering user");
        }
    }

}
