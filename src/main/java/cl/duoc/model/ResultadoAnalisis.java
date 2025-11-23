package cl.duoc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESULTADOS_ANALISIS")
public class ResultadoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESULTADO")
    private Long idResultado;

    @ManyToOne
    @JoinColumn(name = "ID_ASIGNACION", nullable = false)
    private AsignacionLaboratorio asignacion;

    @Column(name = "VALOR_NUMERICO")
    private Double valorNumerico;

    @Column(name = "VALOR_TEXTO", length = 200)
    private String valorTexto;

    @Column(name = "ESTADO_VALIDACION", length = 20)
    private String estadoValidacion;

    @Column(name = "FECHA_REGISTRO")
    private LocalDateTime fechaRegistro;

    @Column(name = "TECNICO_RESPONSABLE", length = 150)
    private String tecnicoResponsable;

    @Column(name = "OBSERVACIONES", length = 400)
    private String observaciones;

    // Getters y setters
}
