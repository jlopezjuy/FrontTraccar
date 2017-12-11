package com.seguritech.traccar.app.service.impl;

import com.seguritech.traccar.app.service.UltimaUbicacionService;
import com.seguritech.traccar.app.domain.UltimaUbicacion;
import com.seguritech.traccar.app.repository.UltimaUbicacionRepository;
import com.seguritech.traccar.app.service.dto.UltimaUbicacionDTO;
import com.seguritech.traccar.app.service.mapper.UltimaUbicacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing UltimaUbicacion.
 */
@Service
@Transactional
public class UltimaUbicacionServiceImpl implements UltimaUbicacionService{

    private final Logger log = LoggerFactory.getLogger(UltimaUbicacionServiceImpl.class);

    private final UltimaUbicacionRepository ultimaUbicacionRepository;

    private final UltimaUbicacionMapper ultimaUbicacionMapper;

    public UltimaUbicacionServiceImpl(UltimaUbicacionRepository ultimaUbicacionRepository, UltimaUbicacionMapper ultimaUbicacionMapper) {
        this.ultimaUbicacionRepository = ultimaUbicacionRepository;
        this.ultimaUbicacionMapper = ultimaUbicacionMapper;
    }

    /**
     * Save a ultimaUbicacion.
     *
     * @param ultimaUbicacionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UltimaUbicacionDTO save(UltimaUbicacionDTO ultimaUbicacionDTO) {
        log.debug("Request to save UltimaUbicacion : {}", ultimaUbicacionDTO);
        UltimaUbicacion ultimaUbicacion = ultimaUbicacionMapper.toEntity(ultimaUbicacionDTO);
        ultimaUbicacion = ultimaUbicacionRepository.save(ultimaUbicacion);
        return ultimaUbicacionMapper.toDto(ultimaUbicacion);
    }

    /**
     * Get all the ultimaUbicacions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UltimaUbicacionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UltimaUbicacions");
        return ultimaUbicacionRepository.findAll(pageable)
            .map(ultimaUbicacionMapper::toDto);
    }

    /**
     * Get one ultimaUbicacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UltimaUbicacionDTO findOne(Long id) {
        log.debug("Request to get UltimaUbicacion : {}", id);
        UltimaUbicacion ultimaUbicacion = ultimaUbicacionRepository.findOne(id);
        return ultimaUbicacionMapper.toDto(ultimaUbicacion);
    }

    /**
     * Delete the ultimaUbicacion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UltimaUbicacion : {}", id);
        ultimaUbicacionRepository.delete(id);
    }
}
