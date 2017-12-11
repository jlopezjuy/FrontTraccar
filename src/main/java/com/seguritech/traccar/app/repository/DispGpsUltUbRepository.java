package com.seguritech.traccar.app.repository;

import com.seguritech.traccar.app.domain.DispGpsUltUb;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DispGpsUltUb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DispGpsUltUbRepository extends JpaRepository<DispGpsUltUb, Long> {

}
