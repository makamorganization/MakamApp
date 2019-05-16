package makam.application.web.rest;

import makam.application.MakamApp;

import makam.application.domain.FieldOfStudyDictionary;
import makam.application.repository.FieldOfStudyDictionaryRepository;
import makam.application.service.FieldOfStudyDictionaryService;
import makam.application.web.rest.errors.ExceptionTranslator;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static makam.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FieldOfStudyDictionaryResource REST controller.
 *
 * @see FieldOfStudyDictionaryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakamApp.class)
public class FieldOfStudyDictionaryResourceIntTest {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private FieldOfStudyDictionaryRepository fieldOfStudyDictionaryRepository;

    @Autowired
    private FieldOfStudyDictionaryService fieldOfStudyDictionaryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFieldOfStudyDictionaryMockMvc;

    private FieldOfStudyDictionary fieldOfStudyDictionary;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FieldOfStudyDictionaryResource fieldOfStudyDictionaryResource = new FieldOfStudyDictionaryResource(fieldOfStudyDictionaryService);
        this.restFieldOfStudyDictionaryMockMvc = MockMvcBuilders.standaloneSetup(fieldOfStudyDictionaryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldOfStudyDictionary createEntity(EntityManager em) {
        FieldOfStudyDictionary fieldOfStudyDictionary = new FieldOfStudyDictionary()
            .key(DEFAULT_KEY)
            .value(DEFAULT_VALUE);
        return fieldOfStudyDictionary;
    }

    @Before
    public void initTest() {
        fieldOfStudyDictionary = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldOfStudyDictionary() throws Exception {
        int databaseSizeBeforeCreate = fieldOfStudyDictionaryRepository.findAll().size();

        // Create the FieldOfStudyDictionary
        restFieldOfStudyDictionaryMockMvc.perform(post("/api/field-of-study-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldOfStudyDictionary)))
            .andExpect(status().isCreated());

        // Validate the FieldOfStudyDictionary in the database
        List<FieldOfStudyDictionary> fieldOfStudyDictionaryList = fieldOfStudyDictionaryRepository.findAll();
        assertThat(fieldOfStudyDictionaryList).hasSize(databaseSizeBeforeCreate + 1);
        FieldOfStudyDictionary testFieldOfStudyDictionary = fieldOfStudyDictionaryList.get(fieldOfStudyDictionaryList.size() - 1);
        assertThat(testFieldOfStudyDictionary.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testFieldOfStudyDictionary.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createFieldOfStudyDictionaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldOfStudyDictionaryRepository.findAll().size();

        // Create the FieldOfStudyDictionary with an existing ID
        fieldOfStudyDictionary.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldOfStudyDictionaryMockMvc.perform(post("/api/field-of-study-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldOfStudyDictionary)))
            .andExpect(status().isBadRequest());

        // Validate the FieldOfStudyDictionary in the database
        List<FieldOfStudyDictionary> fieldOfStudyDictionaryList = fieldOfStudyDictionaryRepository.findAll();
        assertThat(fieldOfStudyDictionaryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFieldOfStudyDictionaries() throws Exception {
        // Initialize the database
        fieldOfStudyDictionaryRepository.saveAndFlush(fieldOfStudyDictionary);

        // Get all the fieldOfStudyDictionaryList
        restFieldOfStudyDictionaryMockMvc.perform(get("/api/field-of-study-dictionaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldOfStudyDictionary.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getFieldOfStudyDictionary() throws Exception {
        // Initialize the database
        fieldOfStudyDictionaryRepository.saveAndFlush(fieldOfStudyDictionary);

        // Get the fieldOfStudyDictionary
        restFieldOfStudyDictionaryMockMvc.perform(get("/api/field-of-study-dictionaries/{id}", fieldOfStudyDictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fieldOfStudyDictionary.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFieldOfStudyDictionary() throws Exception {
        // Get the fieldOfStudyDictionary
        restFieldOfStudyDictionaryMockMvc.perform(get("/api/field-of-study-dictionaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldOfStudyDictionary() throws Exception {
        // Initialize the database
        fieldOfStudyDictionaryService.save(fieldOfStudyDictionary);

        int databaseSizeBeforeUpdate = fieldOfStudyDictionaryRepository.findAll().size();

        // Update the fieldOfStudyDictionary
        FieldOfStudyDictionary updatedFieldOfStudyDictionary = fieldOfStudyDictionaryRepository.findById(fieldOfStudyDictionary.getId()).get();
        // Disconnect from session so that the updates on updatedFieldOfStudyDictionary are not directly saved in db
        em.detach(updatedFieldOfStudyDictionary);
        updatedFieldOfStudyDictionary
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE);

        restFieldOfStudyDictionaryMockMvc.perform(put("/api/field-of-study-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFieldOfStudyDictionary)))
            .andExpect(status().isOk());

        // Validate the FieldOfStudyDictionary in the database
        List<FieldOfStudyDictionary> fieldOfStudyDictionaryList = fieldOfStudyDictionaryRepository.findAll();
        assertThat(fieldOfStudyDictionaryList).hasSize(databaseSizeBeforeUpdate);
        FieldOfStudyDictionary testFieldOfStudyDictionary = fieldOfStudyDictionaryList.get(fieldOfStudyDictionaryList.size() - 1);
        assertThat(testFieldOfStudyDictionary.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testFieldOfStudyDictionary.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldOfStudyDictionary() throws Exception {
        int databaseSizeBeforeUpdate = fieldOfStudyDictionaryRepository.findAll().size();

        // Create the FieldOfStudyDictionary

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldOfStudyDictionaryMockMvc.perform(put("/api/field-of-study-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fieldOfStudyDictionary)))
            .andExpect(status().isBadRequest());

        // Validate the FieldOfStudyDictionary in the database
        List<FieldOfStudyDictionary> fieldOfStudyDictionaryList = fieldOfStudyDictionaryRepository.findAll();
        assertThat(fieldOfStudyDictionaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFieldOfStudyDictionary() throws Exception {
        // Initialize the database
        fieldOfStudyDictionaryService.save(fieldOfStudyDictionary);

        int databaseSizeBeforeDelete = fieldOfStudyDictionaryRepository.findAll().size();

        // Delete the fieldOfStudyDictionary
        restFieldOfStudyDictionaryMockMvc.perform(delete("/api/field-of-study-dictionaries/{id}", fieldOfStudyDictionary.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FieldOfStudyDictionary> fieldOfStudyDictionaryList = fieldOfStudyDictionaryRepository.findAll();
        assertThat(fieldOfStudyDictionaryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldOfStudyDictionary.class);
        FieldOfStudyDictionary fieldOfStudyDictionary1 = new FieldOfStudyDictionary();
        fieldOfStudyDictionary1.setId(1L);
        FieldOfStudyDictionary fieldOfStudyDictionary2 = new FieldOfStudyDictionary();
        fieldOfStudyDictionary2.setId(fieldOfStudyDictionary1.getId());
        assertThat(fieldOfStudyDictionary1).isEqualTo(fieldOfStudyDictionary2);
        fieldOfStudyDictionary2.setId(2L);
        assertThat(fieldOfStudyDictionary1).isNotEqualTo(fieldOfStudyDictionary2);
        fieldOfStudyDictionary1.setId(null);
        assertThat(fieldOfStudyDictionary1).isNotEqualTo(fieldOfStudyDictionary2);
    }
}
