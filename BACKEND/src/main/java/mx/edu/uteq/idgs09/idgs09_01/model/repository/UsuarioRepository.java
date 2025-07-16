package mx.edu.uteq.idgs09.idgs09_01.model.repository;


import mx.edu.uteq.idgs09.idgs09_01.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}

