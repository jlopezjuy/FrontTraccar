package com.seguritech.traccar.app.repository;

import com.seguritech.traccar.app.domain.UltimaUbicacion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UltimaUbicacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UltimaUbicacionRepository extends JpaRepository<UltimaUbicacion, Long> {

}
