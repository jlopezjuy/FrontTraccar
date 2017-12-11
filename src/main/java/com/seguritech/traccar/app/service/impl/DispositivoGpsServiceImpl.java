package com.seguritech.traccar.app.service.impl;

import com.seguritech.traccar.app.service.DispositivoGpsService;
import com.seguritech.traccar.app.domain.DispositivoGps;
import com.seguritech.traccar.app.repository.DispositivoGpsRepository;
import com.seguritech.traccar.app.service.dto.DispositivoGpsDTO;
import com.seguritech.traccar.app.service.mapper.DispositivoGpsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing DispositivoGps.
 */
@Service
@Transactional
public class DispositivoGpsServiceImpl implements DispositivoGpsService{

    private final Logger log = LoggerFactory.getLogger(DispositivoGpsServiceImpl.class);

    private final DispositivoGpsRepository dispositivoGpsRepository;

    private final DispositivoGpsMapper dispositivoGpsMapper;

    public DispositivoGpsServiceImpl(DispositivoGpsRepository dispositivoGpsRepository, DispositivoGpsMapper dispositivoGpsMapper) {
        this.dispositivoGpsRepository = dispositivoGpsRepository;
        this.dispositivoGpsMapper = dispositivoGpsMapper;
    }

    /**
     * Save a dispositivoGps.
     *
     * @param dispositivoGpsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DispositivoGpsDTO save(DispositivoGpsDTO dispositivoGpsDTO) {
        log.debug("Request to save DispositivoGps : {}", dispositivoGpsDTO);
        DispositivoGps dispositivoGps = dispositivoGpsMapper.toEntity(dispositivoGpsDTO);
        dispositivoGps = dispositivoGpsRepository.save(dispositivoGps);
        return dispositivoGpsMapper.toDto(dispositivoGps);
    }

    /**
     * Get all the dispositivoGps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DispositivoGpsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DispositivoGps");
        return dispositivoGpsRepository.findAll(pageable)
            .map(dispositivoGpsMapper::toDto);
    }

    /**
     * Get one dispositivoGps by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DispositivoGpsDTO findOne(Long id) {
        log.debug("Request to get DispositivoGps : {}", id);
        DispositivoGps dispositivoGps = dispositivoGpsRepository.findOne(id);
        return dispositivoGpsMapper.toDto(dispositivoGps);
    }

    /**
     * Delete the dispositivoGps by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DispositivoGps : {}", id);
        dispositivoGpsRepository.delete(id);
    }
}
