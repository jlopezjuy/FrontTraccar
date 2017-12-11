package com.seguritech.traccar.app.web.rest;

import com.seguritech.traccar.app.FrontTraccarApp;

import com.seguritech.traccar.app.domain.DispGpsUltUb;
import com.seguritech.traccar.app.repository.DispGpsUltUbRepository;
import com.seguritech.traccar.app.service.DispGpsUltUbService;
import com.seguritech.traccar.app.service.dto.DispGpsUltUbDTO;
import com.seguritech.traccar.app.service.mapper.DispGpsUltUbMapper;
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
import java.util.List;

import static com.seguritech.traccar.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DispGpsUltUbResource REST controller.
 *
 * @see DispGpsUltUbResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrontTraccarApp.class)
public class DispGpsUltUbResourceIntTest {

    @Autowired
    private DispGpsUltUbRepository dispGpsUltUbRepository;

    @Autowired
    private DispGpsUltUbMapper dispGpsUltUbMapper;

    @Autowired
    private DispGpsUltUbService dispGpsUltUbService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDispGpsUltUbMockMvc;

    private DispGpsUltUb dispGpsUltUb;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DispGpsUltUbResource dispGpsUltUbResource = new DispGpsUltUbResource(dispGpsUltUbService);
        this.restDispGpsUltUbMockMvc = MockMvcBuilders.standaloneSetup(dispGpsUltUbResource)
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
    public static DispGpsUltUb createEntity(EntityManager em) {
        DispGpsUltUb dispGpsUltUb = new DispGpsUltUb();
        return dispGpsUltUb;
    }

    @Before
    public void initTest() {
        dispGpsUltUb = createEntity(em);
    }

    @Test
    @Transactional
    public void createDispGpsUltUb() throws Exception {
        int databaseSizeBeforeCreate = dispGpsUltUbRepository.findAll().size();

        // Create the DispGpsUltUb
        DispGpsUltUbDTO dispGpsUltUbDTO = dispGpsUltUbMapper.toDto(dispGpsUltUb);
        restDispGpsUltUbMockMvc.perform(post("/api/disp-gps-ult-ubs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispGpsUltUbDTO)))
            .andExpect(status().isCreated());

        // Validate the DispGpsUltUb in the database
        List<DispGpsUltUb> dispGpsUltUbList = dispGpsUltUbRepository.findAll();
        assertThat(dispGpsUltUbList).hasSize(databaseSizeBeforeCreate + 1);
        DispGpsUltUb testDispGpsUltUb = dispGpsUltUbList.get(dispGpsUltUbList.size() - 1);
    }

    @Test
    @Transactional
    public void createDispGpsUltUbWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dispGpsUltUbRepository.findAll().size();

        // Create the DispGpsUltUb with an existing ID
        dispGpsUltUb.setId(1L);
        DispGpsUltUbDTO dispGpsUltUbDTO = dispGpsUltUbMapper.toDto(dispGpsUltUb);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDispGpsUltUbMockMvc.perform(post("/api/disp-gps-ult-ubs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispGpsUltUbDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DispGpsUltUb in the database
        List<DispGpsUltUb> dispGpsUltUbList = dispGpsUltUbRepository.findAll();
        assertThat(dispGpsUltUbList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDispGpsUltUbs() throws Exception {
        // Initialize the database
        dispGpsUltUbRepository.saveAndFlush(dispGpsUltUb);

        // Get all the dispGpsUltUbList
        restDispGpsUltUbMockMvc.perform(get("/api/disp-gps-ult-ubs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dispGpsUltUb.getId().intValue())));
    }

    @Test
    @Transactional
    public void getDispGpsUltUb() throws Exception {
        // Initialize the database
        dispGpsUltUbRepository.saveAndFlush(dispGpsUltUb);

        // Get the dispGpsUltUb
        restDispGpsUltUbMockMvc.perform(get("/api/disp-gps-ult-ubs/{id}", dispGpsUltUb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dispGpsUltUb.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDispGpsUltUb() throws Exception {
        // Get the dispGpsUltUb
        restDispGpsUltUbMockMvc.perform(get("/api/disp-gps-ult-ubs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDispGpsUltUb() throws Exception {
        // Initialize the database
        dispGpsUltUbRepository.saveAndFlush(dispGpsUltUb);
        int databaseSizeBeforeUpdate = dispGpsUltUbRepository.findAll().size();

        // Update the dispGpsUltUb
        DispGpsUltUb updatedDispGpsUltUb = dispGpsUltUbRepository.findOne(dispGpsUltUb.getId());
        // Disconnect from session so that the updates on updatedDispGpsUltUb are not directly saved in db
        em.detach(updatedDispGpsUltUb);
        DispGpsUltUbDTO dispGpsUltUbDTO = dispGpsUltUbMapper.toDto(updatedDispGpsUltUb);

        restDispGpsUltUbMockMvc.perform(put("/api/disp-gps-ult-ubs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispGpsUltUbDTO)))
            .andExpect(status().isOk());

        // Validate the DispGpsUltUb in the database
        List<DispGpsUltUb> dispGpsUltUbList = dispGpsUltUbRepository.findAll();
        assertThat(dispGpsUltUbList).hasSize(databaseSizeBeforeUpdate);
        DispGpsUltUb testDispGpsUltUb = dispGpsUltUbList.get(dispGpsUltUbList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingDispGpsUltUb() throws Exception {
        int databaseSizeBeforeUpdate = dispGpsUltUbRepository.findAll().size();

        // Create the DispGpsUltUb
        DispGpsUltUbDTO dispGpsUltUbDTO = dispGpsUltUbMapper.toDto(dispGpsUltUb);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDispGpsUltUbMockMvc.perform(put("/api/disp-gps-ult-ubs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispGpsUltUbDTO)))
            .andExpect(status().isCreated());

        // Validate the DispGpsUltUb in the database
        List<DispGpsUltUb> dispGpsUltUbList = dispGpsUltUbRepository.findAll();
        assertThat(dispGpsUltUbList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDispGpsUltUb() throws Exception {
        // Initialize the database
        dispGpsUltUbRepository.saveAndFlush(dispGpsUltUb);
        int databaseSizeBeforeDelete = dispGpsUltUbRepository.findAll().size();

        // Get the dispGpsUltUb
        restDispGpsUltUbMockMvc.perform(delete("/api/disp-gps-ult-ubs/{id}", dispGpsUltUb.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DispGpsUltUb> dispGpsUltUbList = dispGpsUltUbRepository.findAll();
        assertThat(dispGpsUltUbList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DispGpsUltUb.class);
        DispGpsUltUb dispGpsUltUb1 = new DispGpsUltUb();
        dispGpsUltUb1.setId(1L);
        DispGpsUltUb dispGpsUltUb2 = new DispGpsUltUb();
        dispGpsUltUb2.setId(dispGpsUltUb1.getId());
        assertThat(dispGpsUltUb1).isEqualTo(dispGpsUltUb2);
        dispGpsUltUb2.setId(2L);
        assertThat(dispGpsUltUb1).isNotEqualTo(dispGpsUltUb2);
        dispGpsUltUb1.setId(null);
        assertThat(dispGpsUltUb1).isNotEqualTo(dispGpsUltUb2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DispGpsUltUbDTO.class);
        DispGpsUltUbDTO dispGpsUltUbDTO1 = new DispGpsUltUbDTO();
        dispGpsUltUbDTO1.setId(1L);
        DispGpsUltUbDTO dispGpsUltUbDTO2 = new DispGpsUltUbDTO();
        assertThat(dispGpsUltUbDTO1).isNotEqualTo(dispGpsUltUbDTO2);
        dispGpsUltUbDTO2.setId(dispGpsUltUbDTO1.getId());
        assertThat(dispGpsUltUbDTO1).isEqualTo(dispGpsUltUbDTO2);
        dispGpsUltUbDTO2.setId(2L);
        assertThat(dispGpsUltUbDTO1).isNotEqualTo(dispGpsUltUbDTO2);
        dispGpsUltUbDTO1.setId(null);
        assertThat(dispGpsUltUbDTO1).isNotEqualTo(dispGpsUltUbDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dispGpsUltUbMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dispGpsUltUbMapper.fromId(null)).isNull();
    }
}
