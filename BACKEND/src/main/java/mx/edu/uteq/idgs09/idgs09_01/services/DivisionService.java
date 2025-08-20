package mx.edu.uteq.idgs09.idgs09_01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import mx.edu.uteq.idgs09.idgs09_01.model.repository.DivisionRepo;
import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoRepo;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Division;

@Service
public class DivisionService {
    
    @Autowired
    private DivisionRepo divisionRepo;
    
    @Autowired
    private ProgramaEducativoRepo peRepo;

    @Transactional(readOnly = true)
    public List<Division> buscar(boolean soloActivos) {
        if (soloActivos) {
            return divisionRepo.findAll().stream().filter(Division::isActivo).toList();
        }
        return divisionRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Division> buscarPorId(int id) {
        return divisionRepo.findById(id);
    }

    @Transactional
    public Division crear(Division d) {
        try {
            return divisionRepo.save(d);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Optional<Division> editar(int id, Division division) {
        Optional<Division> opt = divisionRepo.findById(id);
        if (opt.isPresent()) {
            Division d = opt.get();
            d.setNombre(division.getNombre());
            d.setClave(division.getClave());
            d.setActivo(division.isActivo());
            return Optional.of(divisionRepo.save(d));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean borrar(int id) {
        Optional<Division> opt = divisionRepo.findById(id);
        if (opt.isPresent()) {
            Division division = opt.get();
            
            // Verificar si tiene programas educativos asociados
            if (division.getProgramasEducativos() != null && !division.getProgramasEducativos().isEmpty()) {
                return false; // No permitir borrar si tiene datos relacionados
            }
            
            divisionRepo.deleteById(id); // Borrado físico directo
            return true;
        }
        return false;
    }

    @Transactional
    public boolean borrarLogico(int id) {
        Optional<Division> opt = divisionRepo.findById(id);
        if (opt.isPresent()) {
            Division d = opt.get();
            d.setActivo(false); // Borrado lógico (cambiar status)
            divisionRepo.save(d);
            return true;
        }
        return false;
    }
}