package com.seguritech.traccar.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.traccar.app.service.DispositivoGpsService;
import com.seguritech.traccar.app.web.rest.errors.BadRequestAlertException;
import com.seguritech.traccar.app.web.rest.util.HeaderUtil;
import com.seguritech.traccar.app.web.rest.util.PaginationUtil;
import com.seguritech.traccar.app.service.dto.DispositivoGpsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DispositivoGps.
 */
@RestController
@RequestMapping("/api")
public class DispositivoGpsResource {

    private final Logger log = LoggerFactory.getLogger(DispositivoGpsResource.class);

    private static final String ENTITY_NAME = "dispositivoGps";

    private final DispositivoGpsService dispositivoGpsService;

    public DispositivoGpsResource(DispositivoGpsService dispositivoGpsService) {
        this.dispositivoGpsService = dispositivoGpsService;
    }

    /**
     * POST  /dispositivo-gps : Create a new dispositivoGps.
     *
     * @param dispositivoGpsDTO the dispositivoGpsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dispositivoGpsDTO, or with status 400 (Bad Request) if the dispositivoGps has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dispositivo-gps")
    @Timed
    public ResponseEntity<DispositivoGpsDTO> createDispositivoGps(@Valid @RequestBody DispositivoGpsDTO dispositivoGpsDTO) throws URISyntaxException {
        log.debug("REST request to save DispositivoGps : {}", dispositivoGpsDTO);
        if (dispositivoGpsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dispositivoGps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DispositivoGpsDTO result = dispositivoGpsService.save(dispositivoGpsDTO);
        return ResponseEntity.created(new URI("/api/dispositivo-gps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dispositivo-gps : Updates an existing dispositivoGps.
     *
     * @param dispositivoGpsDTO the dispositivoGpsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dispositivoGpsDTO,
     * or with status 400 (Bad Request) if the dispositivoGpsDTO is not valid,
     * or with status 500 (Internal Server Error) if the dispositivoGpsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dispositivo-gps")
    @Timed
    public ResponseEntity<DispositivoGpsDTO> updateDispositivoGps(@Valid @RequestBody DispositivoGpsDTO dispositivoGpsDTO) throws URISyntaxException {
        log.debug("REST request to update DispositivoGps : {}", dispositivoGpsDTO);
        if (dispositivoGpsDTO.getId() == null) {
            return createDispositivoGps(dispositivoGpsDTO);
        }
        DispositivoGpsDTO result = dispositivoGpsService.save(dispositivoGpsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dispositivoGpsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dispositivo-gps : get all the dispositivoGps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dispositivoGps in body
     */
    @GetMapping("/dispositivo-gps")
    @Timed
    public ResponseEntity<List<DispositivoGpsDTO>> getAllDispositivoGps(Pageable pageable) {
        log.debug("REST request to get a page of DispositivoGps");
        Page<DispositivoGpsDTO> page = dispositivoGpsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dispositivo-gps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /dispositivo-gps/:id : get the "id" dispositivoGps.
     *
     * @param id the id of the dispositivoGpsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dispositivoGpsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/dispositivo-gps/{id}")
    @Timed
    public ResponseEntity<DispositivoGpsDTO> getDispositivoGps(@PathVariable Long id) {
        log.debug("REST request to get DispositivoGps : {}", id);
        DispositivoGpsDTO dispositivoGpsDTO = dispositivoGpsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dispositivoGpsDTO));
    }

    /**
     * DELETE  /dispositivo-gps/:id : delete the "id" dispositivoGps.
     *
     * @param id the id of the dispositivoGpsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dispositivo-gps/{id}")
    @Timed
    public ResponseEntity<Void> deleteDispositivoGps(@PathVariable Long id) {
        log.debug("REST request to delete DispositivoGps : {}", id);
        dispositivoGpsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
