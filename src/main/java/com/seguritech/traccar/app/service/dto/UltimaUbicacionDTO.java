package com.seguritech.traccar.app.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UltimaUbicacion entity.
 */
public class UltimaUbicacionDTO implements Serializable {

    private Long id;

    @NotNull
    private String latitud;

    @NotNull
    private String longitud;

    @NotNull
    private String altitud;

    @NotNull
    private String velocidad;

    @NotNull
    private String curso;

    @NotNull
    private String direccion;

    @NotNull
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UltimaUbicacionDTO ultimaUbicacionDTO = (UltimaUbicacionDTO) o;
        if(ultimaUbicacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ultimaUbicacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UltimaUbicacionDTO{" +
            "id=" + getId() +
            ", latitud='" + getLatitud() + "'" +
            ", longitud='" + getLongitud() + "'" +
            ", altitud='" + getAltitud() + "'" +
            ", velocidad='" + getVelocidad() + "'" +
            ", curso='" + getCurso() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
