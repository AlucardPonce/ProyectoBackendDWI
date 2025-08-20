package mx.edu.uteq.idgs09.idgs09_01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Division;

@Repository
public interface DivisionRepo extends JpaRepository<Division, Integer> {
    // JpaRepository ya incluye todos los métodos básicos del CRUD:
    // save(), findAll(), findById(), deleteById(), etc.
}