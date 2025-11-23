// com/tuapp/labs/repository/PacienteRepository.java
package cl.duoc.repository;

import cl.duoc.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByRut(String rut);
}
