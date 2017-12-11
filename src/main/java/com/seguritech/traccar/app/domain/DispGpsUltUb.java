package com.seguritech.traccar.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DispGpsUltUb.
 */
@Entity
@Table(name = "disp_gps_ult_ub")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DispGpsUltUb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DispositivoGps dispositivoGps;

    @ManyToOne
    private UltimaUbicacion ultimaUbicacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DispositivoGps getDispositivoGps() {
        return dispositivoGps;
    }

    public DispGpsUltUb dispositivoGps(DispositivoGps dispositivoGps) {
        this.dispositivoGps = dispositivoGps;
        return this;
    }

    public void setDispositivoGps(DispositivoGps dispositivoGps) {
        this.dispositivoGps = dispositivoGps;
    }

    public UltimaUbicacion getUltimaUbicacion() {
        return ultimaUbicacion;
    }

    public DispGpsUltUb ultimaUbicacion(UltimaUbicacion ultimaUbicacion) {
        this.ultimaUbicacion = ultimaUbicacion;
        return this;
    }

    public void setUltimaUbicacion(UltimaUbicacion ultimaUbicacion) {
        this.ultimaUbicacion = ultimaUbicacion;
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
        DispGpsUltUb dispGpsUltUb = (DispGpsUltUb) o;
        if (dispGpsUltUb.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dispGpsUltUb.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DispGpsUltUb{" +
            "id=" + getId() +
            "}";
    }
}
