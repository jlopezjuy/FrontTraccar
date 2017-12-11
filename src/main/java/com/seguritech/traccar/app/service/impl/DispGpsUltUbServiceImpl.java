package com.seguritech.traccar.app.service.impl;

import com.seguritech.traccar.app.service.DispGpsUltUbService;
import com.seguritech.traccar.app.domain.DispGpsUltUb;
import com.seguritech.traccar.app.repository.DispGpsUltUbRepository;
import com.seguritech.traccar.app.service.dto.DispGpsUltUbDTO;
import com.seguritech.traccar.app.service.mapper.DispGpsUltUbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing DispGpsUltUb.
 */
@Service
@Transactional
public class DispGpsUltUbServiceImpl implements DispGpsUltUbService{

    private final Logger log = LoggerFactory.getLogger(DispGpsUltUbServiceImpl.class);

    private final DispGpsUltUbRepository dispGpsUltUbRepository;

    private final DispGpsUltUbMapper dispGpsUltUbMapper;

    public DispGpsUltUbServiceImpl(DispGpsUltUbRepository dispGpsUltUbRepository, DispGpsUltUbMapper dispGpsUltUbMapper) {
        this.dispGpsUltUbRepository = dispGpsUltUbRepository;
        this.dispGpsUltUbMapper = dispGpsUltUbMapper;
    }

    /**
     * Save a dispGpsUltUb.
     *
     * @param dispGpsUltUbDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DispGpsUltUbDTO save(DispGpsUltUbDTO dispGpsUltUbDTO) {
        log.debug("Request to save DispGpsUltUb : {}", dispGpsUltUbDTO);
        DispGpsUltUb dispGpsUltUb = dispGpsUltUbMapper.toEntity(dispGpsUltUbDTO);
        dispGpsUltUb = dispGpsUltUbRepository.save(dispGpsUltUb);
        return dispGpsUltUbMapper.toDto(dispGpsUltUb);
    }

    /**
     * Get all the dispGpsUltUbs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DispGpsUltUbDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DispGpsUltUbs");
        return dispGpsUltUbRepository.findAll(pageable)
            .map(dispGpsUltUbMapper::toDto);
    }

    /**
     * Get one dispGpsUltUb by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DispGpsUltUbDTO findOne(Long id) {
        log.debug("Request to get DispGpsUltUb : {}", id);
        DispGpsUltUb dispGpsUltUb = dispGpsUltUbRepository.findOne(id);
        return dispGpsUltUbMapper.toDto(dispGpsUltUb);
    }

    /**
     * Delete the dispGpsUltUb by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DispGpsUltUb : {}", id);
        dispGpsUltUbRepository.delete(id);
    }
}
