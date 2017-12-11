package com.seguritech.traccar.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.seguritech.traccar.app.service.UltimaUbicacionService;
import com.seguritech.traccar.app.web.rest.errors.BadRequestAlertException;
import com.seguritech.traccar.app.web.rest.util.HeaderUtil;
import com.seguritech.traccar.app.web.rest.util.PaginationUtil;
import com.seguritech.traccar.app.service.dto.UltimaUbicacionDTO;
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
 * REST controller for managing UltimaUbicacion.
 */
@RestController
@RequestMapping("/api")
public class UltimaUbicacionResource {

    private final Logger log = LoggerFactory.getLogger(UltimaUbicacionResource.class);

    private static final String ENTITY_NAME = "ultimaUbicacion";

    private final UltimaUbicacionService ultimaUbicacionService;

    public UltimaUbicacionResource(UltimaUbicacionService ultimaUbicacionService) {
        this.ultimaUbicacionService = ultimaUbicacionService;
    }

    /**
     * POST  /ultima-ubicacions : Create a new ultimaUbicacion.
     *
     * @param ultimaUbicacionDTO the ultimaUbicacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ultimaUbicacionDTO, or with status 400 (Bad Request) if the ultimaUbicacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ultima-ubicacions")
    @Timed
    public ResponseEntity<UltimaUbicacionDTO> createUltimaUbicacion(@Valid @RequestBody UltimaUbicacionDTO ultimaUbicacionDTO) throws URISyntaxException {
        log.debug("REST request to save UltimaUbicacion : {}", ultimaUbicacionDTO);
        if (ultimaUbicacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new ultimaUbicacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UltimaUbicacionDTO result = ultimaUbicacionService.save(ultimaUbicacionDTO);
        return ResponseEntity.created(new URI("/api/ultima-ubicacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ultima-ubicacions : Updates an existing ultimaUbicacion.
     *
     * @param ultimaUbicacionDTO the ultimaUbicacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ultimaUbicacionDTO,
     * or with status 400 (Bad Request) if the ultimaUbicacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the ultimaUbicacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ultima-ubicacions")
    @Timed
    public ResponseEntity<UltimaUbicacionDTO> updateUltimaUbicacion(@Valid @RequestBody UltimaUbicacionDTO ultimaUbicacionDTO) throws URISyntaxException {
        log.debug("REST request to update UltimaUbicacion : {}", ultimaUbicacionDTO);
        if (ultimaUbicacionDTO.getId() == null) {
            return createUltimaUbicacion(ultimaUbicacionDTO);
        }
        UltimaUbicacionDTO result = ultimaUbicacionService.save(ultimaUbicacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ultimaUbicacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ultima-ubicacions : get all the ultimaUbicacions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ultimaUbicacions in body
     */
    @GetMapping("/ultima-ubicacions")
    @Timed
    public ResponseEntity<List<UltimaUbicacionDTO>> getAllUltimaUbicacions(Pageable pageable) {
        log.debug("REST request to get a page of UltimaUbicacions");
        Page<UltimaUbicacionDTO> page = ultimaUbicacionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ultima-ubicacions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ultima-ubicacions/:id : get the "id" ultimaUbicacion.
     *
     * @param id the id of the ultimaUbicacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ultimaUbicacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ultima-ubicacions/{id}")
    @Timed
    public ResponseEntity<UltimaUbicacionDTO> getUltimaUbicacion(@PathVariable Long id) {
        log.debug("REST request to get UltimaUbicacion : {}", id);
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ultimaUbicacionDTO));
    }

    /**
     * DELETE  /ultima-ubicacions/:id : delete the "id" ultimaUbicacion.
     *
     * @param id the id of the ultimaUbicacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ultima-ubicacions/{id}")
    @Timed
    public ResponseEntity<Void> deleteUltimaUbicacion(@PathVariable Long id) {
        log.debug("REST request to delete UltimaUbicacion : {}", id);
        ultimaUbicacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
