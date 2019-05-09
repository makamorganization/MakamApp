package makam.application.web.rest;

import makam.application.MakamApp;

import makam.application.domain.AchievementDictionary;
import makam.application.repository.AchievementDictionaryRepository;
import makam.application.service.AchievementDictionaryService;
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
 * Test class for the AchievementDictionaryResource REST controller.
 *
 * @see AchievementDictionaryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakamApp.class)
public class AchievementDictionaryResourceIntTest {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    @Autowired
    private AchievementDictionaryRepository achievementDictionaryRepository;

    @Autowired
    private AchievementDictionaryService achievementDictionaryService;

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

    private MockMvc restAchievementDictionaryMockMvc;

    private AchievementDictionary achievementDictionary;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AchievementDictionaryResource achievementDictionaryResource = new AchievementDictionaryResource(achievementDictionaryService);
        this.restAchievementDictionaryMockMvc = MockMvcBuilders.standaloneSetup(achievementDictionaryResource)
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
    public static AchievementDictionary createEntity(EntityManager em) {
        AchievementDictionary achievementDictionary = new AchievementDictionary()
            .key(DEFAULT_KEY)
            .value(DEFAULT_VALUE)
            .enabled(DEFAULT_ENABLED);
        return achievementDictionary;
    }

    @Before
    public void initTest() {
        achievementDictionary = createEntity(em);
    }

    @Test
    @Transactional
    public void createAchievementDictionary() throws Exception {
        int databaseSizeBeforeCreate = achievementDictionaryRepository.findAll().size();

        // Create the AchievementDictionary
        restAchievementDictionaryMockMvc.perform(post("/api/achievement-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achievementDictionary)))
            .andExpect(status().isCreated());

        // Validate the AchievementDictionary in the database
        List<AchievementDictionary> achievementDictionaryList = achievementDictionaryRepository.findAll();
        assertThat(achievementDictionaryList).hasSize(databaseSizeBeforeCreate + 1);
        AchievementDictionary testAchievementDictionary = achievementDictionaryList.get(achievementDictionaryList.size() - 1);
        assertThat(testAchievementDictionary.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testAchievementDictionary.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testAchievementDictionary.isEnabled()).isEqualTo(DEFAULT_ENABLED);
    }

    @Test
    @Transactional
    public void createAchievementDictionaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = achievementDictionaryRepository.findAll().size();

        // Create the AchievementDictionary with an existing ID
        achievementDictionary.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchievementDictionaryMockMvc.perform(post("/api/achievement-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achievementDictionary)))
            .andExpect(status().isBadRequest());

        // Validate the AchievementDictionary in the database
        List<AchievementDictionary> achievementDictionaryList = achievementDictionaryRepository.findAll();
        assertThat(achievementDictionaryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAchievementDictionaries() throws Exception {
        // Initialize the database
        achievementDictionaryRepository.saveAndFlush(achievementDictionary);

        // Get all the achievementDictionaryList
        restAchievementDictionaryMockMvc.perform(get("/api/achievement-dictionaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achievementDictionary.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAchievementDictionary() throws Exception {
        // Initialize the database
        achievementDictionaryRepository.saveAndFlush(achievementDictionary);

        // Get the achievementDictionary
        restAchievementDictionaryMockMvc.perform(get("/api/achievement-dictionaries/{id}", achievementDictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(achievementDictionary.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAchievementDictionary() throws Exception {
        // Get the achievementDictionary
        restAchievementDictionaryMockMvc.perform(get("/api/achievement-dictionaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAchievementDictionary() throws Exception {
        // Initialize the database
        achievementDictionaryService.save(achievementDictionary);

        int databaseSizeBeforeUpdate = achievementDictionaryRepository.findAll().size();

        // Update the achievementDictionary
        AchievementDictionary updatedAchievementDictionary = achievementDictionaryRepository.findById(achievementDictionary.getId()).get();
        // Disconnect from session so that the updates on updatedAchievementDictionary are not directly saved in db
        em.detach(updatedAchievementDictionary);
        updatedAchievementDictionary
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE)
            .enabled(UPDATED_ENABLED);

        restAchievementDictionaryMockMvc.perform(put("/api/achievement-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAchievementDictionary)))
            .andExpect(status().isOk());

        // Validate the AchievementDictionary in the database
        List<AchievementDictionary> achievementDictionaryList = achievementDictionaryRepository.findAll();
        assertThat(achievementDictionaryList).hasSize(databaseSizeBeforeUpdate);
        AchievementDictionary testAchievementDictionary = achievementDictionaryList.get(achievementDictionaryList.size() - 1);
        assertThat(testAchievementDictionary.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testAchievementDictionary.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testAchievementDictionary.isEnabled()).isEqualTo(UPDATED_ENABLED);
    }

    @Test
    @Transactional
    public void updateNonExistingAchievementDictionary() throws Exception {
        int databaseSizeBeforeUpdate = achievementDictionaryRepository.findAll().size();

        // Create the AchievementDictionary

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchievementDictionaryMockMvc.perform(put("/api/achievement-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(achievementDictionary)))
            .andExpect(status().isBadRequest());

        // Validate the AchievementDictionary in the database
        List<AchievementDictionary> achievementDictionaryList = achievementDictionaryRepository.findAll();
        assertThat(achievementDictionaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAchievementDictionary() throws Exception {
        // Initialize the database
        achievementDictionaryService.save(achievementDictionary);

        int databaseSizeBeforeDelete = achievementDictionaryRepository.findAll().size();

        // Delete the achievementDictionary
        restAchievementDictionaryMockMvc.perform(delete("/api/achievement-dictionaries/{id}", achievementDictionary.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AchievementDictionary> achievementDictionaryList = achievementDictionaryRepository.findAll();
        assertThat(achievementDictionaryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchievementDictionary.class);
        AchievementDictionary achievementDictionary1 = new AchievementDictionary();
        achievementDictionary1.setId(1L);
        AchievementDictionary achievementDictionary2 = new AchievementDictionary();
        achievementDictionary2.setId(achievementDictionary1.getId());
        assertThat(achievementDictionary1).isEqualTo(achievementDictionary2);
        achievementDictionary2.setId(2L);
        assertThat(achievementDictionary1).isNotEqualTo(achievementDictionary2);
        achievementDictionary1.setId(null);
        assertThat(achievementDictionary1).isNotEqualTo(achievementDictionary2);
    }
}
