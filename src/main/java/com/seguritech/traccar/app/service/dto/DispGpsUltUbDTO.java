package com.seguritech.traccar.app.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DispGpsUltUb entity.
 */
public class DispGpsUltUbDTO implements Serializable {

    private Long id;

    private Long dispositivoGpsId;

    private String dispositivoGpsIdDispositivo;

    private Long ultimaUbicacionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDispositivoGpsId() {
        return dispositivoGpsId;
    }

    public void setDispositivoGpsId(Long dispositivoGpsId) {
        this.dispositivoGpsId = dispositivoGpsId;
    }

    public String getDispositivoGpsIdDispositivo() {
        return dispositivoGpsIdDispositivo;
    }

    public void setDispositivoGpsIdDispositivo(String dispositivoGpsIdDispositivo) {
        this.dispositivoGpsIdDispositivo = dispositivoGpsIdDispositivo;
    }

    public Long getUltimaUbicacionId() {
        return ultimaUbicacionId;
    }

    public void setUltimaUbicacionId(Long ultimaUbicacionId) {
        this.ultimaUbicacionId = ultimaUbicacionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DispGpsUltUbDTO dispGpsUltUbDTO = (DispGpsUltUbDTO) o;
        if(dispGpsUltUbDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dispGpsUltUbDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DispGpsUltUbDTO{" +
            "id=" + getId() +
            "}";
    }
}
