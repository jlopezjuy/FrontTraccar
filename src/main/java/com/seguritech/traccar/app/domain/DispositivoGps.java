package com.seguritech.traccar.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DispositivoGps.
 */
@Entity
@Table(name = "dispositivo_gps")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DispositivoGps implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_dispositivo", nullable = false)
    private String idDispositivo;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public DispositivoGps idDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
        return this;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public DispositivoGps fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DispositivoGps dispositivoGps = (DispositivoGps) o;
        if (dispositivoGps.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dispositivoGps.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DispositivoGps{" +
            "id=" + getId() +
            ", idDispositivo='" + getIdDispositivo() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            "}";
    }
}
