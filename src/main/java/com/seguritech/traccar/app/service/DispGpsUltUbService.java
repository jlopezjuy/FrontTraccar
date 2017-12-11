package com.seguritech.traccar.app.service;

import com.seguritech.traccar.app.service.dto.DispGpsUltUbDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DispGpsUltUb.
 */
public interface DispGpsUltUbService {

    /**
     * Save a dispGpsUltUb.
     *
     * @param dispGpsUltUbDTO the entity to save
     * @return the persisted entity
     */
    DispGpsUltUbDTO save(DispGpsUltUbDTO dispGpsUltUbDTO);

    /**
     * Get all the dispGpsUltUbs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DispGpsUltUbDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dispGpsUltUb.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DispGpsUltUbDTO findOne(Long id);

    /**
     * Delete the "id" dispGpsUltUb.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
