package com.seguritech.traccar.app.web.rest;

import com.seguritech.traccar.app.FrontTraccarApp;

import com.seguritech.traccar.app.domain.UltimaUbicacion;
import com.seguritech.traccar.app.repository.UltimaUbicacionRepository;
import com.seguritech.traccar.app.service.UltimaUbicacionService;
import com.seguritech.traccar.app.service.dto.UltimaUbicacionDTO;
import com.seguritech.traccar.app.service.mapper.UltimaUbicacionMapper;
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
 * Test class for the UltimaUbicacionResource REST controller.
 *
 * @see UltimaUbicacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrontTraccarApp.class)
public class UltimaUbicacionResourceIntTest {

    private static final String DEFAULT_LATITUD = "AAAAAAAAAA";
    private static final String UPDATED_LATITUD = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUD = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUD = "BBBBBBBBBB";

    private static final String DEFAULT_ALTITUD = "AAAAAAAAAA";
    private static final String UPDATED_ALTITUD = "BBBBBBBBBB";

    private static final String DEFAULT_VELOCIDAD = "AAAAAAAAAA";
    private static final String UPDATED_VELOCIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_CURSO = "AAAAAAAAAA";
    private static final String UPDATED_CURSO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UltimaUbicacionRepository ultimaUbicacionRepository;

    @Autowired
    private UltimaUbicacionMapper ultimaUbicacionMapper;

    @Autowired
    private UltimaUbicacionService ultimaUbicacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUltimaUbicacionMockMvc;

    private UltimaUbicacion ultimaUbicacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UltimaUbicacionResource ultimaUbicacionResource = new UltimaUbicacionResource(ultimaUbicacionService);
        this.restUltimaUbicacionMockMvc = MockMvcBuilders.standaloneSetup(ultimaUbicacionResource)
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
    public static UltimaUbicacion createEntity(EntityManager em) {
        UltimaUbicacion ultimaUbicacion = new UltimaUbicacion()
            .latitud(DEFAULT_LATITUD)
            .longitud(DEFAULT_LONGITUD)
            .altitud(DEFAULT_ALTITUD)
            .velocidad(DEFAULT_VELOCIDAD)
            .curso(DEFAULT_CURSO)
            .direccion(DEFAULT_DIRECCION)
            .fecha(DEFAULT_FECHA);
        return ultimaUbicacion;
    }

    @Before
    public void initTest() {
        ultimaUbicacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createUltimaUbicacion() throws Exception {
        int databaseSizeBeforeCreate = ultimaUbicacionRepository.findAll().size();

        // Create the UltimaUbicacion
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);
        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isCreated());

        // Validate the UltimaUbicacion in the database
        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeCreate + 1);
        UltimaUbicacion testUltimaUbicacion = ultimaUbicacionList.get(ultimaUbicacionList.size() - 1);
        assertThat(testUltimaUbicacion.getLatitud()).isEqualTo(DEFAULT_LATITUD);
        assertThat(testUltimaUbicacion.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
        assertThat(testUltimaUbicacion.getAltitud()).isEqualTo(DEFAULT_ALTITUD);
        assertThat(testUltimaUbicacion.getVelocidad()).isEqualTo(DEFAULT_VELOCIDAD);
        assertThat(testUltimaUbicacion.getCurso()).isEqualTo(DEFAULT_CURSO);
        assertThat(testUltimaUbicacion.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testUltimaUbicacion.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    public void createUltimaUbicacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ultimaUbicacionRepository.findAll().size();

        // Create the UltimaUbicacion with an existing ID
        ultimaUbicacion.setId(1L);
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UltimaUbicacion in the database
        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLatitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = ultimaUbicacionRepository.findAll().size();
        // set the field null
        ultimaUbicacion.setLatitud(null);

        // Create the UltimaUbicacion, which fails.
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = ultimaUbicacionRepository.findAll().size();
        // set the field null
        ultimaUbicacion.setLongitud(null);

        // Create the UltimaUbicacion, which fails.
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAltitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = ultimaUbicacionRepository.findAll().size();
        // set the field null
        ultimaUbicacion.setAltitud(null);

        // Create the UltimaUbicacion, which fails.
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVelocidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = ultimaUbicacionRepository.findAll().size();
        // set the field null
        ultimaUbicacion.setVelocidad(null);

        // Create the UltimaUbicacion, which fails.
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCursoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ultimaUbicacionRepository.findAll().size();
        // set the field null
        ultimaUbicacion.setCurso(null);

        // Create the UltimaUbicacion, which fails.
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDireccionIsRequired() throws Exception {
        int databaseSizeBeforeTest = ultimaUbicacionRepository.findAll().size();
        // set the field null
        ultimaUbicacion.setDireccion(null);

        // Create the UltimaUbicacion, which fails.
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = ultimaUbicacionRepository.findAll().size();
        // set the field null
        ultimaUbicacion.setFecha(null);

        // Create the UltimaUbicacion, which fails.
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        restUltimaUbicacionMockMvc.perform(post("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isBadRequest());

        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUltimaUbicacions() throws Exception {
        // Initialize the database
        ultimaUbicacionRepository.saveAndFlush(ultimaUbicacion);

        // Get all the ultimaUbicacionList
        restUltimaUbicacionMockMvc.perform(get("/api/ultima-ubicacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ultimaUbicacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD.toString())))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD.toString())))
            .andExpect(jsonPath("$.[*].altitud").value(hasItem(DEFAULT_ALTITUD.toString())))
            .andExpect(jsonPath("$.[*].velocidad").value(hasItem(DEFAULT_VELOCIDAD.toString())))
            .andExpect(jsonPath("$.[*].curso").value(hasItem(DEFAULT_CURSO.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }

    @Test
    @Transactional
    public void getUltimaUbicacion() throws Exception {
        // Initialize the database
        ultimaUbicacionRepository.saveAndFlush(ultimaUbicacion);

        // Get the ultimaUbicacion
        restUltimaUbicacionMockMvc.perform(get("/api/ultima-ubicacions/{id}", ultimaUbicacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ultimaUbicacion.getId().intValue()))
            .andExpect(jsonPath("$.latitud").value(DEFAULT_LATITUD.toString()))
            .andExpect(jsonPath("$.longitud").value(DEFAULT_LONGITUD.toString()))
            .andExpect(jsonPath("$.altitud").value(DEFAULT_ALTITUD.toString()))
            .andExpect(jsonPath("$.velocidad").value(DEFAULT_VELOCIDAD.toString()))
            .andExpect(jsonPath("$.curso").value(DEFAULT_CURSO.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUltimaUbicacion() throws Exception {
        // Get the ultimaUbicacion
        restUltimaUbicacionMockMvc.perform(get("/api/ultima-ubicacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUltimaUbicacion() throws Exception {
        // Initialize the database
        ultimaUbicacionRepository.saveAndFlush(ultimaUbicacion);
        int databaseSizeBeforeUpdate = ultimaUbicacionRepository.findAll().size();

        // Update the ultimaUbicacion
        UltimaUbicacion updatedUltimaUbicacion = ultimaUbicacionRepository.findOne(ultimaUbicacion.getId());
        // Disconnect from session so that the updates on updatedUltimaUbicacion are not directly saved in db
        em.detach(updatedUltimaUbicacion);
        updatedUltimaUbicacion
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD)
            .altitud(UPDATED_ALTITUD)
            .velocidad(UPDATED_VELOCIDAD)
            .curso(UPDATED_CURSO)
            .direccion(UPDATED_DIRECCION)
            .fecha(UPDATED_FECHA);
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(updatedUltimaUbicacion);

        restUltimaUbicacionMockMvc.perform(put("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isOk());

        // Validate the UltimaUbicacion in the database
        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeUpdate);
        UltimaUbicacion testUltimaUbicacion = ultimaUbicacionList.get(ultimaUbicacionList.size() - 1);
        assertThat(testUltimaUbicacion.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testUltimaUbicacion.getLongitud()).isEqualTo(UPDATED_LONGITUD);
        assertThat(testUltimaUbicacion.getAltitud()).isEqualTo(UPDATED_ALTITUD);
        assertThat(testUltimaUbicacion.getVelocidad()).isEqualTo(UPDATED_VELOCIDAD);
        assertThat(testUltimaUbicacion.getCurso()).isEqualTo(UPDATED_CURSO);
        assertThat(testUltimaUbicacion.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testUltimaUbicacion.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void updateNonExistingUltimaUbicacion() throws Exception {
        int databaseSizeBeforeUpdate = ultimaUbicacionRepository.findAll().size();

        // Create the UltimaUbicacion
        UltimaUbicacionDTO ultimaUbicacionDTO = ultimaUbicacionMapper.toDto(ultimaUbicacion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUltimaUbicacionMockMvc.perform(put("/api/ultima-ubicacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ultimaUbicacionDTO)))
            .andExpect(status().isCreated());

        // Validate the UltimaUbicacion in the database
        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUltimaUbicacion() throws Exception {
        // Initialize the database
        ultimaUbicacionRepository.saveAndFlush(ultimaUbicacion);
        int databaseSizeBeforeDelete = ultimaUbicacionRepository.findAll().size();

        // Get the ultimaUbicacion
        restUltimaUbicacionMockMvc.perform(delete("/api/ultima-ubicacions/{id}", ultimaUbicacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UltimaUbicacion> ultimaUbicacionList = ultimaUbicacionRepository.findAll();
        assertThat(ultimaUbicacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UltimaUbicacion.class);
        UltimaUbicacion ultimaUbicacion1 = new UltimaUbicacion();
        ultimaUbicacion1.setId(1L);
        UltimaUbicacion ultimaUbicacion2 = new UltimaUbicacion();
        ultimaUbicacion2.setId(ultimaUbicacion1.getId());
        assertThat(ultimaUbicacion1).isEqualTo(ultimaUbicacion2);
        ultimaUbicacion2.setId(2L);
        assertThat(ultimaUbicacion1).isNotEqualTo(ultimaUbicacion2);
        ultimaUbicacion1.setId(null);
        assertThat(ultimaUbicacion1).isNotEqualTo(ultimaUbicacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UltimaUbicacionDTO.class);
        UltimaUbicacionDTO ultimaUbicacionDTO1 = new UltimaUbicacionDTO();
        ultimaUbicacionDTO1.setId(1L);
        UltimaUbicacionDTO ultimaUbicacionDTO2 = new UltimaUbicacionDTO();
        assertThat(ultimaUbicacionDTO1).isNotEqualTo(ultimaUbicacionDTO2);
        ultimaUbicacionDTO2.setId(ultimaUbicacionDTO1.getId());
        assertThat(ultimaUbicacionDTO1).isEqualTo(ultimaUbicacionDTO2);
        ultimaUbicacionDTO2.setId(2L);
        assertThat(ultimaUbicacionDTO1).isNotEqualTo(ultimaUbicacionDTO2);
        ultimaUbicacionDTO1.setId(null);
        assertThat(ultimaUbicacionDTO1).isNotEqualTo(ultimaUbicacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ultimaUbicacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ultimaUbicacionMapper.fromId(null)).isNull();
    }
}
