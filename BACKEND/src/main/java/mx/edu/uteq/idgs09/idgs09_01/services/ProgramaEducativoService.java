package mx.edu.uteq.idgs09.idgs09_01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;

import java.util.List;
import java.util.Optional;

import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoRepo;

@Service
public class ProgramaEducativoService {
    @Autowired
    private ProgramaEducativoRepo repo;

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
        return repo.save(pe);
    }

    @Transactional
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Transactional
    public Optional<Profesor> asignarProfesor(Profesor p, Long peId) {
        Optional<ProgramaEducativo> opt = repo.findById(peId);
        if (opt.isPresent()) {
            ProgramaEducativo pe = opt.get();

            Profesor profesorMsvc = client.porId(p.getId());
            profesorMsvc.setClavePe(pe.getClave());
            client.editarProfesor(p.getId(), profesorMsvc);

            ProgramaEducativoProfesor progEduProf = new ProgramaEducativoProfesor();
            progEduProf.setProfesorId(profesorMsvc.getId());

            pe.addProgramaEducativoProfesor(progEduProf);
            repo.save(pe);
            return Optional.of(profesorMsvc);
        }
        return Optional.empty();
    }

}
