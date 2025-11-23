package cl.duoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TIPOS_ANALISIS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ANALISIS")
    private Long idTipoAnalisis;

    @Column(name = "NOMBRE", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "CODIGO", nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(name = "UNIDAD_MEDIDA", length = 30)
    private String unidadMedida;

    @Column(name = "RANGO_REFERENCIA", length = 80)
    private String rangoReferencia;

    @OneToMany(mappedBy = "tipoAnalisis", fetch = FetchType.LAZY)
    @JsonIgnore // 👈 evita ciclo y respuestas gigantes
    private List<AsignacionLaboratorio> asignaciones;

    public TipoAnalisis() {}

    public Long getIdTipoAnalisis() { return idTipoAnalisis; }
    public void setIdTipoAnalisis(Long idTipoAnalisis) { this.idTipoAnalisis = idTipoAnalisis; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public String getRangoReferencia() { return rangoReferencia; }
    public void setRangoReferencia(String rangoReferencia) { this.rangoReferencia = rangoReferencia; }

    public List<AsignacionLaboratorio> getAsignaciones() { return asignaciones; }
    public void setAsignaciones(List<AsignacionLaboratorio> asignaciones) { this.asignaciones = asignaciones; }
}
