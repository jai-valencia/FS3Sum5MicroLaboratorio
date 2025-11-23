package cl.duoc.repository;


import cl.duoc.model.AsignacionLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignacionLaboratorioRepository extends JpaRepository<AsignacionLaboratorio, Long> {
    List<AsignacionLaboratorio> findByLaboratorio_IdLaboratorio(Long idLaboratorio);
    List<AsignacionLaboratorio> findByOrdenAnalisis_IdOrdenAnalisis(Long idOrden);
}

