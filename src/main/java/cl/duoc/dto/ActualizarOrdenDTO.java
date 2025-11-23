// com/duoc/labs/dto/ActualizarOrdenDTO.java
package cl.duoc.dto;

import jakarta.validation.constraints.Size;

public class ActualizarOrdenDTO {
    @Size(max = 120)
    private String medicoSolicitante;

    @Size(max = 500)
    private String observaciones;

    public String getMedicoSolicitante() { return medicoSolicitante; }
    public void setMedicoSolicitante(String medicoSolicitante) { this.medicoSolicitante = medicoSolicitante; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}

