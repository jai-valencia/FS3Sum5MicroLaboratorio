package cl.duoc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CrearOrdenDTO {
    @NotBlank @Size(max=20)  private String rutPaciente;    
    @NotBlank @Size(max=150) private String medicoSolicitante;
    @Size(max=400) private String observaciones;
    // getters/setters
    public String getRutPaciente() {
        return rutPaciente;
    }
    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }
    public String getMedicoSolicitante() {
        return medicoSolicitante;
    }
    public void setMedicoSolicitante(String medicoSolicitante) {
        this.medicoSolicitante = medicoSolicitante;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}

