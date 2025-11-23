package com.duoc.labs.service;

import com.duoc.fslaboratorio.model.Laboratorio;
import com.duoc.labs.dto.CrearLaboratorioDTO;
import com.duoc.labs.repository.LaboratorioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LaboratorioService {

    private final LaboratorioRepository labRepo;

    public LaboratorioService(LaboratorioRepository labRepo) {
        this.labRepo = labRepo;
    }

    public Laboratorio crear(CrearLaboratorioDTO dto){
        Laboratorio l = new Laboratorio();
        l.setNombre(dto.getNombre());
        l.setCodigo(dto.getCodigo());
        l.setDireccion(dto.getDireccion());
        l.setTelefono(dto.getTelefono());
        l.setEstado("ACTIVO");
        return labRepo.save(l);
    }

    public Laboratorio obtener(Long id){
        return labRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Laboratorio no encontrado"));
    }

    public List<Laboratorio> listarTodos(){
    return labRepo.findAll();
    }
}
