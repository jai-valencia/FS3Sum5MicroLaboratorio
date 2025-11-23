package cl.duoc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ASIGNACIONES_LABORATORIO")
public class AsignacionLaboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASIGNACION")
    private Long idAsignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDEN_ANALISIS", nullable = false)
    @JsonBackReference(value = "orden-asignaciones")
    private OrdenAnalisis ordenAnalisis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LABORATORIO", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Laboratorio laboratorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_ANALISIS", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoAnalisis tipoAnalisis;

    @Column(name = "FECHA_ASIGNACION", nullable = false)
    private LocalDateTime fechaAsignacion;

    @Column(name = "ESTADO", length = 20, nullable = false)
    private String estado;

    @Column(name = "PRIORIDAD", length = 10, nullable = false)
    private String prioridad;

    public AsignacionLaboratorio() {}

    public Long getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(Long idAsignacion) { this.idAsignacion = idAsignacion; }

    public OrdenAnalisis getOrdenAnalisis() { return ordenAnalisis; }
    public void setOrdenAnalisis(OrdenAnalisis ordenAnalisis) { this.ordenAnalisis = ordenAnalisis; }

    public Laboratorio getLaboratorio() { return laboratorio; }
    public void setLaboratorio(Laboratorio laboratorio) { this.laboratorio = laboratorio; }

    public TipoAnalisis getTipoAnalisis() { return tipoAnalisis; }
    public void setTipoAnalisis(TipoAnalisis tipoAnalisis) { this.tipoAnalisis = tipoAnalisis; }

    public LocalDateTime getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDateTime fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
}
