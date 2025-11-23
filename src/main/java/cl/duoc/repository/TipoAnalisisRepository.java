// com/tuapp/labs/repository/TipoAnalisisRepository.java
package cl.duoc.repository;

import cl.duoc.model.TipoAnalisis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoAnalisisRepository extends JpaRepository<TipoAnalisis, Long> {
    Optional<TipoAnalisis> findByCodigo(String codigo);
}

