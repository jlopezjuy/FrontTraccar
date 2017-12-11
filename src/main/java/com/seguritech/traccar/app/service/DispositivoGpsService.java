package com.seguritech.traccar.app.service;

import com.seguritech.traccar.app.service.dto.DispositivoGpsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DispositivoGps.
 */
public interface DispositivoGpsService {

    /**
     * Save a dispositivoGps.
     *
     * @param dispositivoGpsDTO the entity to save
     * @return the persisted entity
     */
    DispositivoGpsDTO save(DispositivoGpsDTO dispositivoGpsDTO);

    /**
     * Get all the dispositivoGps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DispositivoGpsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dispositivoGps.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DispositivoGpsDTO findOne(Long id);

    /**
     * Delete the "id" dispositivoGps.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
