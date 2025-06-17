package mx.edu.uteq.idgs09.idgs09_01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Profesor;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;
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
    public ProgramaEducativo save(ProgramaEducativo pe) {
        if (pe.getIdPro() != null) {
            try {
                // Llama al microservicio de profesores para validar que existe
                Profesor profesor = client.porId(pe.getIdPro());
                if (profesor == null) {
                    throw new IllegalArgumentException("El profesor no existe en el microservicio externo.");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("El profesor no existe o el microservicio no responde.");
            }
        }
        return repo.save(pe);
    }

    @Transactional
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Transactional
    public Optional<Profesor> asignarProfesor(Profesor p, int peId) {
        Optional<ProgramaEducativo> opt = repo.findById(peId);
        if (opt.isPresent()) {
            ProgramaEducativo pe = opt.get();

            // Verifica que el profesor exista en el microservicio externo
            Profesor profesorMsvc = client.porId(p.getId());
            if (profesorMsvc != null) {
                // Asigna el id del profesor al programa educativo
                pe.setIdPro(profesorMsvc.getId());
                repo.save(pe);

                // --- Sincroniza el campo id_pe en el microservicio de profesores ---
                // Usa la clave del programa educativo
                profesorMsvc.setId_pe(pe.getClave());
                client.editarProfesor(profesorMsvc.getId(), profesorMsvc);

                return Optional.of(profesorMsvc);
            }
        }
        return Optional.empty();
    }

    @Transactional
    public Profesor editarProfesor(int id, Profesor p) {
        // Llama al microservicio de profesor para editar
        return client.editarProfesor(id, p);
    }

    public Profesor obtenerProfesorDePrograma(ProgramaEducativo pe) {
        if (pe.getIdPro() != null) {
            return client.porId(pe.getIdPro());
        }
        return null;
    }

    @Transactional(readOnly = true)
    public ProgramaEducativo BuscarPorClave(String clave) {
        return repo.findByClave(clave);
    }
}
