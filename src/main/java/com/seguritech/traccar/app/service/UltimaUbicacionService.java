package com.seguritech.traccar.app.service;

import com.seguritech.traccar.app.service.dto.UltimaUbicacionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UltimaUbicacion.
 */
public interface UltimaUbicacionService {

    /**
     * Save a ultimaUbicacion.
     *
     * @param ultimaUbicacionDTO the entity to save
     * @return the persisted entity
     */
    UltimaUbicacionDTO save(UltimaUbicacionDTO ultimaUbicacionDTO);

    /**
     * Get all the ultimaUbicacions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UltimaUbicacionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ultimaUbicacion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UltimaUbicacionDTO findOne(Long id);

    /**
     * Delete the "id" ultimaUbicacion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
