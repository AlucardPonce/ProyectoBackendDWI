package mx.edu.uteq.idgs09.idgs09_01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaEducativoService {

    @Autowired
    private ProgramaEducativoRepo repo;

    @Transactional(readOnly = true)
    public List<ProgramaEducativo> buscar(boolean soloActivos) {
        if (soloActivos) {
            return repo.findAll().stream().filter(ProgramaEducativo::isActivo).toList();
        }
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProgramaEducativo> buscarPorId(int id) {
        return repo.findById(id);
    }

    @Transactional
    public ProgramaEducativo crear(ProgramaEducativo pe) {
        try {
            return repo.save(pe);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Optional<ProgramaEducativo> editar(int id, ProgramaEducativo programaEducativo) {
        Optional<ProgramaEducativo> opt = repo.findById(id);
        if (opt.isPresent()) {
            ProgramaEducativo pe = opt.get();
            pe.setProgramaEducativo(programaEducativo.getProgramaEducativo());
            pe.setClave(programaEducativo.getClave());
            pe.setActivo(programaEducativo.isActivo());
            pe.setDivision(programaEducativo.getDivision());
            return Optional.of(repo.save(pe));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean borrar(int id) {
        Optional<ProgramaEducativo> opt = repo.findById(id);
        if (opt.isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public ProgramaEducativo buscarPorClave(String clave) {
        // Usar stream para buscar por clave (sin método en repo)
        return repo.findAll().stream()
                .filter(pe -> pe.getClave().equals(clave))
                .findFirst()
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ProgramaEducativo> buscarPorDivision(int divisionId, boolean soloActivos) {
        return repo.findAll().stream()
                .filter(pe -> pe.getDivision() != null && pe.getDivision().getId() == divisionId)
                .filter(pe -> !soloActivos || pe.isActivo())
                .toList();
    }
}