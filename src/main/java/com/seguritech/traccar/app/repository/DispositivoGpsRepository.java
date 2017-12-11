package com.seguritech.traccar.app.repository;

import com.seguritech.traccar.app.domain.DispositivoGps;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DispositivoGps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DispositivoGpsRepository extends JpaRepository<DispositivoGps, Long> {

}
