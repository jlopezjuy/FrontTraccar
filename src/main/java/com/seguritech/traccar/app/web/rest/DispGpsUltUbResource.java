package com.seguritech.traccar.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.traccar.app.service.DispGpsUltUbService;
import com.seguritech.traccar.app.web.rest.errors.BadRequestAlertException;
import com.seguritech.traccar.app.web.rest.util.HeaderUtil;
import com.seguritech.traccar.app.web.rest.util.PaginationUtil;
import com.seguritech.traccar.app.service.dto.DispGpsUltUbDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DispGpsUltUb.
 */
@RestController
@RequestMapping("/api")
public class DispGpsUltUbResource {

    private final Logger log = LoggerFactory.getLogger(DispGpsUltUbResource.class);

    private static final String ENTITY_NAME = "dispGpsUltUb";

    private final DispGpsUltUbService dispGpsUltUbService;

    public DispGpsUltUbResource(DispGpsUltUbService dispGpsUltUbService) {
        this.dispGpsUltUbService = dispGpsUltUbService;
    }

    /**
     * POST  /disp-gps-ult-ubs : Create a new dispGpsUltUb.
     *
     * @param dispGpsUltUbDTO the dispGpsUltUbDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dispGpsUltUbDTO, or with status 400 (Bad Request) if the dispGpsUltUb has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disp-gps-ult-ubs")
    @Timed
    public ResponseEntity<DispGpsUltUbDTO> createDispGpsUltUb(@RequestBody DispGpsUltUbDTO dispGpsUltUbDTO) throws URISyntaxException {
        log.debug("REST request to save DispGpsUltUb : {}", dispGpsUltUbDTO);
        if (dispGpsUltUbDTO.getId() != null) {
            throw new BadRequestAlertException("A new dispGpsUltUb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DispGpsUltUbDTO result = dispGpsUltUbService.save(dispGpsUltUbDTO);
        return ResponseEntity.created(new URI("/api/disp-gps-ult-ubs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disp-gps-ult-ubs : Updates an existing dispGpsUltUb.
     *
     * @param dispGpsUltUbDTO the dispGpsUltUbDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dispGpsUltUbDTO,
     * or with status 400 (Bad Request) if the dispGpsUltUbDTO is not valid,
     * or with status 500 (Internal Server Error) if the dispGpsUltUbDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disp-gps-ult-ubs")
    @Timed
    public ResponseEntity<DispGpsUltUbDTO> updateDispGpsUltUb(@RequestBody DispGpsUltUbDTO dispGpsUltUbDTO) throws URISyntaxException {
        log.debug("REST request to update DispGpsUltUb : {}", dispGpsUltUbDTO);
        if (dispGpsUltUbDTO.getId() == null) {
            return createDispGpsUltUb(dispGpsUltUbDTO);
        }
        DispGpsUltUbDTO result = dispGpsUltUbService.save(dispGpsUltUbDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dispGpsUltUbDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disp-gps-ult-ubs : get all the dispGpsUltUbs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dispGpsUltUbs in body
     */
    @GetMapping("/disp-gps-ult-ubs")
    @Timed
    public ResponseEntity<List<DispGpsUltUbDTO>> getAllDispGpsUltUbs(Pageable pageable) {
        log.debug("REST request to get a page of DispGpsUltUbs");
        Page<DispGpsUltUbDTO> page = dispGpsUltUbService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/disp-gps-ult-ubs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /disp-gps-ult-ubs/:id : get the "id" dispGpsUltUb.
     *
     * @param id the id of the dispGpsUltUbDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dispGpsUltUbDTO, or with status 404 (Not Found)
     */
    @GetMapping("/disp-gps-ult-ubs/{id}")
    @Timed
    public ResponseEntity<DispGpsUltUbDTO> getDispGpsUltUb(@PathVariable Long id) {
        log.debug("REST request to get DispGpsUltUb : {}", id);
        DispGpsUltUbDTO dispGpsUltUbDTO = dispGpsUltUbService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dispGpsUltUbDTO));
    }

    /**
     * DELETE  /disp-gps-ult-ubs/:id : delete the "id" dispGpsUltUb.
     *
     * @param id the id of the dispGpsUltUbDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disp-gps-ult-ubs/{id}")
    @Timed
    public ResponseEntity<Void> deleteDispGpsUltUb(@PathVariable Long id) {
        log.debug("REST request to delete DispGpsUltUb : {}", id);
        dispGpsUltUbService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
