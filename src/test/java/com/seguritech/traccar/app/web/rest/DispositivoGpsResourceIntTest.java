package com.seguritech.traccar.app.web.rest;

import com.seguritech.traccar.app.FrontTraccarApp;

import com.seguritech.traccar.app.domain.DispositivoGps;
import com.seguritech.traccar.app.repository.DispositivoGpsRepository;
import com.seguritech.traccar.app.service.DispositivoGpsService;
import com.seguritech.traccar.app.service.dto.DispositivoGpsDTO;
import com.seguritech.traccar.app.service.mapper.DispositivoGpsMapper;
import com.seguritech.traccar.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.seguritech.traccar.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DispositivoGpsResource REST controller.
 *
 * @see DispositivoGpsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrontTraccarApp.class)
public class DispositivoGpsResourceIntTest {

    private static final String DEFAULT_ID_DISPOSITIVO = "AAAAAAAAAA";
    private static final String UPDATED_ID_DISPOSITIVO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DispositivoGpsRepository dispositivoGpsRepository;

    @Autowired
    private DispositivoGpsMapper dispositivoGpsMapper;

    @Autowired
    private DispositivoGpsService dispositivoGpsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDispositivoGpsMockMvc;

    private DispositivoGps dispositivoGps;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DispositivoGpsResource dispositivoGpsResource = new DispositivoGpsResource(dispositivoGpsService);
        this.restDispositivoGpsMockMvc = MockMvcBuilders.standaloneSetup(dispositivoGpsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DispositivoGps createEntity(EntityManager em) {
        DispositivoGps dispositivoGps = new DispositivoGps()
            .idDispositivo(DEFAULT_ID_DISPOSITIVO)
            .fechaCreacion(DEFAULT_FECHA_CREACION);
        return dispositivoGps;
    }

    @Before
    public void initTest() {
        dispositivoGps = createEntity(em);
    }

    @Test
    @Transactional
    public void createDispositivoGps() throws Exception {
        int databaseSizeBeforeCreate = dispositivoGpsRepository.findAll().size();

        // Create the DispositivoGps
        DispositivoGpsDTO dispositivoGpsDTO = dispositivoGpsMapper.toDto(dispositivoGps);
        restDispositivoGpsMockMvc.perform(post("/api/dispositivo-gps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispositivoGpsDTO)))
            .andExpect(status().isCreated());

        // Validate the DispositivoGps in the database
        List<DispositivoGps> dispositivoGpsList = dispositivoGpsRepository.findAll();
        assertThat(dispositivoGpsList).hasSize(databaseSizeBeforeCreate + 1);
        DispositivoGps testDispositivoGps = dispositivoGpsList.get(dispositivoGpsList.size() - 1);
        assertThat(testDispositivoGps.getIdDispositivo()).isEqualTo(DEFAULT_ID_DISPOSITIVO);
        assertThat(testDispositivoGps.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void createDispositivoGpsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dispositivoGpsRepository.findAll().size();

        // Create the DispositivoGps with an existing ID
        dispositivoGps.setId(1L);
        DispositivoGpsDTO dispositivoGpsDTO = dispositivoGpsMapper.toDto(dispositivoGps);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDispositivoGpsMockMvc.perform(post("/api/dispositivo-gps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispositivoGpsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DispositivoGps in the database
        List<DispositivoGps> dispositivoGpsList = dispositivoGpsRepository.findAll();
        assertThat(dispositivoGpsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdDispositivoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dispositivoGpsRepository.findAll().size();
        // set the field null
        dispositivoGps.setIdDispositivo(null);

        // Create the DispositivoGps, which fails.
        DispositivoGpsDTO dispositivoGpsDTO = dispositivoGpsMapper.toDto(dispositivoGps);

        restDispositivoGpsMockMvc.perform(post("/api/dispositivo-gps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispositivoGpsDTO)))
            .andExpect(status().isBadRequest());

        List<DispositivoGps> dispositivoGpsList = dispositivoGpsRepository.findAll();
        assertThat(dispositivoGpsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = dispositivoGpsRepository.findAll().size();
        // set the field null
        dispositivoGps.setFechaCreacion(null);

        // Create the DispositivoGps, which fails.
        DispositivoGpsDTO dispositivoGpsDTO = dispositivoGpsMapper.toDto(dispositivoGps);

        restDispositivoGpsMockMvc.perform(post("/api/dispositivo-gps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispositivoGpsDTO)))
            .andExpect(status().isBadRequest());

        List<DispositivoGps> dispositivoGpsList = dispositivoGpsRepository.findAll();
        assertThat(dispositivoGpsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDispositivoGps() throws Exception {
        // Initialize the database
        dispositivoGpsRepository.saveAndFlush(dispositivoGps);

        // Get all the dispositivoGpsList
        restDispositivoGpsMockMvc.perform(get("/api/dispositivo-gps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dispositivoGps.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDispositivo").value(hasItem(DEFAULT_ID_DISPOSITIVO.toString())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())));
    }

    @Test
    @Transactional
    public void getDispositivoGps() throws Exception {
        // Initialize the database
        dispositivoGpsRepository.saveAndFlush(dispositivoGps);

        // Get the dispositivoGps
        restDispositivoGpsMockMvc.perform(get("/api/dispositivo-gps/{id}", dispositivoGps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dispositivoGps.getId().intValue()))
            .andExpect(jsonPath("$.idDispositivo").value(DEFAULT_ID_DISPOSITIVO.toString()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDispositivoGps() throws Exception {
        // Get the dispositivoGps
        restDispositivoGpsMockMvc.perform(get("/api/dispositivo-gps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDispositivoGps() throws Exception {
        // Initialize the database
        dispositivoGpsRepository.saveAndFlush(dispositivoGps);
        int databaseSizeBeforeUpdate = dispositivoGpsRepository.findAll().size();

        // Update the dispositivoGps
        DispositivoGps updatedDispositivoGps = dispositivoGpsRepository.findOne(dispositivoGps.getId());
        // Disconnect from session so that the updates on updatedDispositivoGps are not directly saved in db
        em.detach(updatedDispositivoGps);
        updatedDispositivoGps
            .idDispositivo(UPDATED_ID_DISPOSITIVO)
            .fechaCreacion(UPDATED_FECHA_CREACION);
        DispositivoGpsDTO dispositivoGpsDTO = dispositivoGpsMapper.toDto(updatedDispositivoGps);

        restDispositivoGpsMockMvc.perform(put("/api/dispositivo-gps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispositivoGpsDTO)))
            .andExpect(status().isOk());

        // Validate the DispositivoGps in the database
        List<DispositivoGps> dispositivoGpsList = dispositivoGpsRepository.findAll();
        assertThat(dispositivoGpsList).hasSize(databaseSizeBeforeUpdate);
        DispositivoGps testDispositivoGps = dispositivoGpsList.get(dispositivoGpsList.size() - 1);
        assertThat(testDispositivoGps.getIdDispositivo()).isEqualTo(UPDATED_ID_DISPOSITIVO);
        assertThat(testDispositivoGps.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void updateNonExistingDispositivoGps() throws Exception {
        int databaseSizeBeforeUpdate = dispositivoGpsRepository.findAll().size();

        // Create the DispositivoGps
        DispositivoGpsDTO dispositivoGpsDTO = dispositivoGpsMapper.toDto(dispositivoGps);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDispositivoGpsMockMvc.perform(put("/api/dispositivo-gps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispositivoGpsDTO)))
            .andExpect(status().isCreated());

        // Validate the DispositivoGps in the database
        List<DispositivoGps> dispositivoGpsList = dispositivoGpsRepository.findAll();
        assertThat(dispositivoGpsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDispositivoGps() throws Exception {
        // Initialize the database
        dispositivoGpsRepository.saveAndFlush(dispositivoGps);
        int databaseSizeBeforeDelete = dispositivoGpsRepository.findAll().size();

        // Get the dispositivoGps
        restDispositivoGpsMockMvc.perform(delete("/api/dispositivo-gps/{id}", dispositivoGps.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DispositivoGps> dispositivoGpsList = dispositivoGpsRepository.findAll();
        assertThat(dispositivoGpsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DispositivoGps.class);
        DispositivoGps dispositivoGps1 = new DispositivoGps();
        dispositivoGps1.setId(1L);
        DispositivoGps dispositivoGps2 = new DispositivoGps();
        dispositivoGps2.setId(dispositivoGps1.getId());
        assertThat(dispositivoGps1).isEqualTo(dispositivoGps2);
        dispositivoGps2.setId(2L);
        assertThat(dispositivoGps1).isNotEqualTo(dispositivoGps2);
        dispositivoGps1.setId(null);
        assertThat(dispositivoGps1).isNotEqualTo(dispositivoGps2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DispositivoGpsDTO.class);
        DispositivoGpsDTO dispositivoGpsDTO1 = new DispositivoGpsDTO();
        dispositivoGpsDTO1.setId(1L);
        DispositivoGpsDTO dispositivoGpsDTO2 = new DispositivoGpsDTO();
        assertThat(dispositivoGpsDTO1).isNotEqualTo(dispositivoGpsDTO2);
        dispositivoGpsDTO2.setId(dispositivoGpsDTO1.getId());
        assertThat(dispositivoGpsDTO1).isEqualTo(dispositivoGpsDTO2);
        dispositivoGpsDTO2.setId(2L);
        assertThat(dispositivoGpsDTO1).isNotEqualTo(dispositivoGpsDTO2);
        dispositivoGpsDTO1.setId(null);
        assertThat(dispositivoGpsDTO1).isNotEqualTo(dispositivoGpsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dispositivoGpsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dispositivoGpsMapper.fromId(null)).isNull();
    }
}
