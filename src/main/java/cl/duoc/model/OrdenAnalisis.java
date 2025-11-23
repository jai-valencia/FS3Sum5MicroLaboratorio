package cl.duoc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDENES_ANALISIS")
public class OrdenAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN_ANALISIS")
    private Long idOrdenAnalisis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    // Evita problemas de proxies LAZY y evita serializar la colección ordenes del paciente (para no ciclar)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "ordenes"})
    private Paciente paciente;

    @Column(name = "FECHA_SOLICITUD", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado;

    @Column(name = "MEDICO_SOLICITANTE", length = 150)
    private String medicoSolicitante;

    @Column(name = "OBSERVACIONES", length = 400)
    private String observaciones;

    @OneToMany(mappedBy = "ordenAnalisis", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "orden-asignaciones")
    private List<AsignacionLaboratorio> asignaciones;

    public OrdenAnalisis() {}

    public Long getIdOrdenAnalisis() { return idOrdenAnalisis; }
    public void setIdOrdenAnalisis(Long idOrdenAnalisis) { this.idOrdenAnalisis = idOrdenAnalisis; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMedicoSolicitante() { return medicoSolicitante; }
    public void setMedicoSolicitante(String medicoSolicitante) { this.medicoSolicitante = medicoSolicitante; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<AsignacionLaboratorio> getAsignaciones() { return asignaciones; }
    public void setAsignaciones(List<AsignacionLaboratorio> asignaciones) { this.asignaciones = asignaciones; }
}
