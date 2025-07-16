package mx.edu.uteq.idgs09.idgs09_01.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Usuario;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean authenticate(String username, String password) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
        if (optionalUsuario.isPresent()) {
            String hashed = optionalUsuario.get().getPassword();
            System.out.println("Hash from DB: " + hashed);
            System.out.println("Password input: " + password);
            boolean result = BCrypt.checkpw(password, hashed);
            System.out.println("Password match: " + result);
            return result;
        }
        return false;
    }

    public Usuario registerUser(String username, String password) {
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    Usuario usuario = new Usuario();
    usuario.setUsername(username);
    usuario.setPassword(hashedPassword);
    return usuarioRepository.save(usuario);
}

}
