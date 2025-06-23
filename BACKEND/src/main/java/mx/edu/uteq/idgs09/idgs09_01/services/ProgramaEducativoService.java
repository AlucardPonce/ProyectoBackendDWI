package mx.edu.uteq.idgs09.idgs09_01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Profesor;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoRepo;
import mx.edu.uteq.idgs09.idgs09_01.clients.ProfesorClientRest;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaEducativoService {

    @Autowired
    private ProgramaEducativoRepo repo;

    @Autowired
    private ProfesorClientRest client;

    @Transactional(readOnly = true)
    public List<ProgramaEducativo> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProgramaEducativo> findById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public void deleteById(int id) {
        repo.deleteById(id);
    }



    //@Transactional
   // public Profesor editarProfesor(int id, Profesor p) {
        // Llama al microservicio de profesor para editar
     //   return client.editarProfesor(id, p);
    //}



    @Transactional(readOnly = true)
    public ProgramaEducativo BuscarPorClave(String clave) {
        return repo.findByClave(clave);
    }
}
