package cl.duoc.controller;


import cl.duoc.fslaboratorio.model.OrdenAnalisis;
import cl.duoc.dto.AsignarAnalisisDTO;
import cl.duoc.dto.CambiarEstadoAsignacionDTO;
import cl.duoc.dto.CambiarEstadoOrdenDTO;
import cl.duoc.dto.CrearOrdenDTO;
import cl.duoc.dto.ActualizarOrdenDTO;
import cl.duoc.service.OrdenAsignacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/labs")
public class OrdenAsignacionController {

    private final OrdenAsignacionService service;

    public OrdenAsignacionController(OrdenAsignacionService service) {
        this.service = service;
    }

    // ---------- ÓRDENES ----------
    // Crear orden
    @PostMapping("/ordenes")
    public ResponseEntity<OrdenAnalisis> crearOrden(@Valid @RequestBody CrearOrdenDTO dto){
        OrdenAnalisis oa = service.crearOrden(dto);
        return ResponseEntity.created(URI.create("/api/labs/ordenes/" + oa.getIdOrdenAnalisis()))
                             .body(oa);
    }

    // ✅ Listar todas
    @GetMapping("/ordenes")
    public ResponseEntity<List<OrdenAnalisis>> listarOrdenes(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String rutPaciente
    ){
        if (estado != null) return ResponseEntity.ok(service.listarPorEstado(estado));
        if (rutPaciente != null) return ResponseEntity.ok(service.listarPorRutPaciente(rutPaciente));
        return ResponseEntity.ok(service.listarTodas());
    }

    // Obtener por id
    @GetMapping("/ordenes/{id}")
    public ResponseEntity<OrdenAnalisis> obtenerOrden(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // Actualizar campos simples
    @PutMapping("/ordenes/{id}")
    public ResponseEntity<OrdenAnalisis> actualizarOrden(@PathVariable Long id,
                                                         @Valid @RequestBody ActualizarOrdenDTO dto){
        return ResponseEntity.ok(service.actualizarOrden(id, dto.getMedicoSolicitante(), dto.getObservaciones()));
    }

    // Cambiar estado de la orden
    @PutMapping("/ordenes/{id}/estado")
    public ResponseEntity<OrdenAnalisis> cambiarEstadoOrden(@PathVariable Long id,
                                                            @Valid @RequestBody CambiarEstadoOrdenDTO dto){
        return ResponseEntity.ok(service.cambiarEstadoOrden(id, dto.getNuevoEstado()));
    }

    // Eliminar
    @DeleteMapping("/ordenes/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id){
        service.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- ASIGNACIONES ----------
    @PostMapping("/asignaciones")
    public ResponseEntity<?> asignarAnalisis(@Valid @RequestBody AsignarAnalisisDTO dto){
        return ResponseEntity.ok(service.asignarAnalisis(dto));
    }

    @PutMapping("/asignaciones/estado")
    public ResponseEntity<?> cambiarEstado(@Valid @RequestBody CambiarEstadoAsignacionDTO dto){
        return ResponseEntity.ok(service.cambiarEstado(dto));
    }
}
