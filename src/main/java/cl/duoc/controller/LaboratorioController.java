// com/duoc/labs/controller/LaboratorioController.java
package cl.duoc.controller;

import cl.duoc.model.Laboratorio;
import cl.duoc.dto.CrearLaboratorioDTO;
import cl.duoc.service.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/labs") 
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    // POST /api/labs/laboratorios
    @PostMapping("/laboratorios")
    public ResponseEntity<Laboratorio> crear(@Valid @RequestBody CrearLaboratorioDTO dto){
        Laboratorio l = laboratorioService.crear(dto);
        return ResponseEntity.created(URI.create("/api/labs/laboratorios/" + l.getIdLaboratorio()))
                             .body(l);
    }

    // GET /api/labs/laboratorios/{id}
    @GetMapping("/laboratorios/{id}")
    public ResponseEntity<Laboratorio> obtener(@PathVariable Long id){
        return ResponseEntity.ok(laboratorioService.obtener(id));
    }

    // GET /api/labs/laboratorios (listar todos)
    @GetMapping("/laboratorios")
    public ResponseEntity<List<Laboratorio>> listar(){
        return ResponseEntity.ok(laboratorioService.listarTodos());
    }

   
    @GetMapping
    public ResponseEntity<List<Laboratorio>> listarEnRaiz(){
        return ResponseEntity.ok(laboratorioService.listarTodos());
    }
}
