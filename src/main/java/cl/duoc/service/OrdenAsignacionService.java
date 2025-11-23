package cl.duoc.service;


import cl.duoc.model.*;
import cl.duoc.dto.AsignarAnalisisDTO;
import cl.duoc.dto.CambiarEstadoAsignacionDTO;
import cl.duoc.dto.CrearOrdenDTO;
import cl.duoc.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrdenAsignacionService {

    private final PacienteRepository pacienteRepo;
    private final OrdenAnalisisRepository ordenRepo;
    private final LaboratorioRepository labRepo;
    private final TipoAnalisisRepository tipoRepo;
    private final AsignacionLaboratorioRepository asignRepo;

    public OrdenAsignacionService(PacienteRepository pacienteRepo, OrdenAnalisisRepository ordenRepo,
                                  LaboratorioRepository labRepo, TipoAnalisisRepository tipoRepo,
                                  AsignacionLaboratorioRepository asignRepo) {
        this.pacienteRepo = pacienteRepo;
        this.ordenRepo = ordenRepo;
        this.labRepo = labRepo;
        this.tipoRepo = tipoRepo;
        this.asignRepo = asignRepo;
    }

    public OrdenAnalisis crearOrden(CrearOrdenDTO dto){
        Paciente p = pacienteRepo.findByRut(dto.getRutPaciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado por RUT"));

        OrdenAnalisis oa = new OrdenAnalisis();
        oa.setPaciente(p);
        oa.setFechaSolicitud(LocalDateTime.now());
        oa.setEstado("PENDIENTE");
        oa.setMedicoSolicitante(dto.getMedicoSolicitante());
        oa.setObservaciones(dto.getObservaciones());
        return ordenRepo.save(oa);
    }

    public AsignacionLaboratorio asignarAnalisis(AsignarAnalisisDTO dto){
        OrdenAnalisis orden = ordenRepo.findById(dto.getIdOrdenAnalisis())
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));
        Laboratorio lab = labRepo.findByCodigo(dto.getCodigoLaboratorio())
                .orElseThrow(() -> new EntityNotFoundException("Laboratorio no encontrado por código"));
        TipoAnalisis tipo = tipoRepo.findByCodigo(dto.getCodigoTipoAnalisis())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de análisis no encontrado por código"));

        AsignacionLaboratorio asig = new AsignacionLaboratorio();
        asig.setOrdenAnalisis(orden);
        asig.setLaboratorio(lab);
        asig.setTipoAnalisis(tipo);
        asig.setFechaAsignacion(LocalDateTime.now());
        asig.setEstado("ASIGNADA");
        asig.setPrioridad(dto.getPrioridad()==null ? "MEDIA" : dto.getPrioridad());

        // opcional: si estaba PENDIENTE, pásala a ASIGNADA
        if ("PENDIENTE".equalsIgnoreCase(orden.getEstado())){
            orden.setEstado("ASIGNADA");
        }
        return asignRepo.save(asig);
    }

    public AsignacionLaboratorio cambiarEstado(CambiarEstadoAsignacionDTO dto){
        AsignacionLaboratorio a = asignRepo.findById(dto.getIdAsignacion())
                .orElseThrow(() -> new EntityNotFoundException("Asignación no encontrada"));
        a.setEstado(dto.getEstado());
        // si finaliza, podrías marcar la orden COMPLETADA si todas sus asignaciones finalizaron
        return a; // JPA hace flush al terminar la transacción
    }

    public List<OrdenAnalisis> listarTodas() {
        return ordenRepo.findAll();
    }

    public OrdenAnalisis obtenerPorId(Long id) {
        return ordenRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));
    }

    public List<OrdenAnalisis> listarPorEstado(String estado) {
        return ordenRepo.findByEstadoIgnoreCase(estado);
    }

    public List<OrdenAnalisis> listarPorRutPaciente(String rutPaciente) {
        return ordenRepo.findByPaciente_Rut(rutPaciente);
    }

    public OrdenAnalisis actualizarOrden(Long id, String medicoSolicitante, String observaciones) {
        OrdenAnalisis oa = obtenerPorId(id);
        if (medicoSolicitante != null) oa.setMedicoSolicitante(medicoSolicitante);
        if (observaciones != null) oa.setObservaciones(observaciones);
        return oa; // JPA flush al final
    }

    public OrdenAnalisis cambiarEstadoOrden(Long id, String nuevoEstado) {
        OrdenAnalisis oa = obtenerPorId(id);
        oa.setEstado(nuevoEstado);
        return oa;
    }

    public void eliminarOrden(Long id) {
        if (!ordenRepo.existsById(id)) throw new EntityNotFoundException("Orden no encontrada");
        ordenRepo.deleteById(id);
    }
  


    
}

