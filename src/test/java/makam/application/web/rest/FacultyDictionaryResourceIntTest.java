package makam.application.web.rest;

import makam.application.MakamApp;

import makam.application.domain.FacultyDictionary;
import makam.application.repository.FacultyDictionaryRepository;
import makam.application.service.FacultyDictionaryService;
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
 * Test class for the FacultyDictionaryResource REST controller.
 *
 * @see FacultyDictionaryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakamApp.class)
public class FacultyDictionaryResourceIntTest {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private FacultyDictionaryRepository facultyDictionaryRepository;

    @Autowired
    private FacultyDictionaryService facultyDictionaryService;

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

    private MockMvc restFacultyDictionaryMockMvc;

    private FacultyDictionary facultyDictionary;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FacultyDictionaryResource facultyDictionaryResource = new FacultyDictionaryResource(facultyDictionaryService);
        this.restFacultyDictionaryMockMvc = MockMvcBuilders.standaloneSetup(facultyDictionaryResource)
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
    public static FacultyDictionary createEntity(EntityManager em) {
        FacultyDictionary facultyDictionary = new FacultyDictionary()
            .key(DEFAULT_KEY)
            .value(DEFAULT_VALUE);
        return facultyDictionary;
    }

    @Before
    public void initTest() {
        facultyDictionary = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacultyDictionary() throws Exception {
        int databaseSizeBeforeCreate = facultyDictionaryRepository.findAll().size();

        // Create the FacultyDictionary
        restFacultyDictionaryMockMvc.perform(post("/api/faculty-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facultyDictionary)))
            .andExpect(status().isCreated());

        // Validate the FacultyDictionary in the database
        List<FacultyDictionary> facultyDictionaryList = facultyDictionaryRepository.findAll();
        assertThat(facultyDictionaryList).hasSize(databaseSizeBeforeCreate + 1);
        FacultyDictionary testFacultyDictionary = facultyDictionaryList.get(facultyDictionaryList.size() - 1);
        assertThat(testFacultyDictionary.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testFacultyDictionary.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createFacultyDictionaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facultyDictionaryRepository.findAll().size();

        // Create the FacultyDictionary with an existing ID
        facultyDictionary.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacultyDictionaryMockMvc.perform(post("/api/faculty-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facultyDictionary)))
            .andExpect(status().isBadRequest());

        // Validate the FacultyDictionary in the database
        List<FacultyDictionary> facultyDictionaryList = facultyDictionaryRepository.findAll();
        assertThat(facultyDictionaryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFacultyDictionaries() throws Exception {
        // Initialize the database
        facultyDictionaryRepository.saveAndFlush(facultyDictionary);

        // Get all the facultyDictionaryList
        restFacultyDictionaryMockMvc.perform(get("/api/faculty-dictionaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facultyDictionary.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getFacultyDictionary() throws Exception {
        // Initialize the database
        facultyDictionaryRepository.saveAndFlush(facultyDictionary);

        // Get the facultyDictionary
        restFacultyDictionaryMockMvc.perform(get("/api/faculty-dictionaries/{id}", facultyDictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(facultyDictionary.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFacultyDictionary() throws Exception {
        // Get the facultyDictionary
        restFacultyDictionaryMockMvc.perform(get("/api/faculty-dictionaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacultyDictionary() throws Exception {
        // Initialize the database
        facultyDictionaryService.save(facultyDictionary);

        int databaseSizeBeforeUpdate = facultyDictionaryRepository.findAll().size();

        // Update the facultyDictionary
        FacultyDictionary updatedFacultyDictionary = facultyDictionaryRepository.findById(facultyDictionary.getId()).get();
        // Disconnect from session so that the updates on updatedFacultyDictionary are not directly saved in db
        em.detach(updatedFacultyDictionary);
        updatedFacultyDictionary
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE);

        restFacultyDictionaryMockMvc.perform(put("/api/faculty-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFacultyDictionary)))
            .andExpect(status().isOk());

        // Validate the FacultyDictionary in the database
        List<FacultyDictionary> facultyDictionaryList = facultyDictionaryRepository.findAll();
        assertThat(facultyDictionaryList).hasSize(databaseSizeBeforeUpdate);
        FacultyDictionary testFacultyDictionary = facultyDictionaryList.get(facultyDictionaryList.size() - 1);
        assertThat(testFacultyDictionary.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testFacultyDictionary.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingFacultyDictionary() throws Exception {
        int databaseSizeBeforeUpdate = facultyDictionaryRepository.findAll().size();

        // Create the FacultyDictionary

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFacultyDictionaryMockMvc.perform(put("/api/faculty-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facultyDictionary)))
            .andExpect(status().isBadRequest());

        // Validate the FacultyDictionary in the database
        List<FacultyDictionary> facultyDictionaryList = facultyDictionaryRepository.findAll();
        assertThat(facultyDictionaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFacultyDictionary() throws Exception {
        // Initialize the database
        facultyDictionaryService.save(facultyDictionary);

        int databaseSizeBeforeDelete = facultyDictionaryRepository.findAll().size();

        // Delete the facultyDictionary
        restFacultyDictionaryMockMvc.perform(delete("/api/faculty-dictionaries/{id}", facultyDictionary.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FacultyDictionary> facultyDictionaryList = facultyDictionaryRepository.findAll();
        assertThat(facultyDictionaryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacultyDictionary.class);
        FacultyDictionary facultyDictionary1 = new FacultyDictionary();
        facultyDictionary1.setId(1L);
        FacultyDictionary facultyDictionary2 = new FacultyDictionary();
        facultyDictionary2.setId(facultyDictionary1.getId());
        assertThat(facultyDictionary1).isEqualTo(facultyDictionary2);
        facultyDictionary2.setId(2L);
        assertThat(facultyDictionary1).isNotEqualTo(facultyDictionary2);
        facultyDictionary1.setId(null);
        assertThat(facultyDictionary1).isNotEqualTo(facultyDictionary2);
    }
}
