package com.seguritech.traccar.app.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DispositivoGps entity.
 */
public class DispositivoGpsDTO implements Serializable {

    private Long id;

    @NotNull
    private String idDispositivo;

    @NotNull
    private LocalDate fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DispositivoGpsDTO dispositivoGpsDTO = (DispositivoGpsDTO) o;
        if(dispositivoGpsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dispositivoGpsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DispositivoGpsDTO{" +
            "id=" + getId() +
            ", idDispositivo='" + getIdDispositivo() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            "}";
    }
}
