package cl.duoc.repository;


import cl.duoc.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    Optional<Laboratorio> findByCodigo(String codigo);
}
