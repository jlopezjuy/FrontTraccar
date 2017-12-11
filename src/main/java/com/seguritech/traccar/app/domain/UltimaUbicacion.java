package com.seguritech.traccar.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A UltimaUbicacion.
 */
@Entity
@Table(name = "ultima_ubicacion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UltimaUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "latitud", nullable = false)
    private String latitud;

    @NotNull
    @Column(name = "longitud", nullable = false)
    private String longitud;

    @NotNull
    @Column(name = "altitud", nullable = false)
    private String altitud;

    @NotNull
    @Column(name = "velocidad", nullable = false)
    private String velocidad;

    @NotNull
    @Column(name = "curso", nullable = false)
    private String curso;

    @NotNull
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public UltimaUbicacion latitud(String latitud) {
        this.latitud = latitud;
        return this;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public UltimaUbicacion longitud(String longitud) {
        this.longitud = longitud;
        return this;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAltitud() {
        return altitud;
    }

    public UltimaUbicacion altitud(String altitud) {
        this.altitud = altitud;
        return this;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public UltimaUbicacion velocidad(String velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getCurso() {
        return curso;
    }

    public UltimaUbicacion curso(String curso) {
        this.curso = curso;
        return this;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDireccion() {
        return direccion;
    }

    public UltimaUbicacion direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public UltimaUbicacion fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
        UltimaUbicacion ultimaUbicacion = (UltimaUbicacion) o;
        if (ultimaUbicacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ultimaUbicacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UltimaUbicacion{" +
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
