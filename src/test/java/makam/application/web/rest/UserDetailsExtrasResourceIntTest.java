package makam.application.web.rest;

import makam.application.MakamApp;

import makam.application.domain.UserDetailsExtras;
import makam.application.repository.UserDetailsExtrasRepository;
import makam.application.service.UserDetailsExtrasService;
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
 * Test class for the UserDetailsExtrasResource REST controller.
 *
 * @see UserDetailsExtrasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakamApp.class)
public class UserDetailsExtrasResourceIntTest {

    private static final Integer DEFAULT_NUMBER_OF_POINTS = 1;
    private static final Integer UPDATED_NUMBER_OF_POINTS = 2;

    private static final Integer DEFAULT_NUMBER_OF_BEING_LATE_IN_ROW = 1;
    private static final Integer UPDATED_NUMBER_OF_BEING_LATE_IN_ROW = 2;

    private static final Integer DEFAULT_NUMBER_OF_COURSES_FINISHED = 1;
    private static final Integer UPDATED_NUMBER_OF_COURSES_FINISHED = 2;

    private static final Integer DEFAULT_NUMBER_OF_BEING_LATE_TOTAL = 1;
    private static final Integer UPDATED_NUMBER_OF_BEING_LATE_TOTAL = 2;

    private static final Integer DEFAULT_NUMBER_OF_COURSES_TOTAL_OMITED = 1;
    private static final Integer UPDATED_NUMBER_OF_COURSES_TOTAL_OMITED = 2;

    private static final Integer DEFAULT_NUMBER_OF_COURSES_OMITED_IN_ROW = 1;
    private static final Integer UPDATED_NUMBER_OF_COURSES_OMITED_IN_ROW = 2;

    @Autowired
    private UserDetailsExtrasRepository userDetailsExtrasRepository;

    @Autowired
    private UserDetailsExtrasService userDetailsExtrasService;

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

    private MockMvc restUserDetailsExtrasMockMvc;

    private UserDetailsExtras userDetailsExtras;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserDetailsExtrasResource userDetailsExtrasResource = new UserDetailsExtrasResource(userDetailsExtrasService);
        this.restUserDetailsExtrasMockMvc = MockMvcBuilders.standaloneSetup(userDetailsExtrasResource)
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
    public static UserDetailsExtras createEntity(EntityManager em) {
        UserDetailsExtras userDetailsExtras = new UserDetailsExtras()
            .numberOfPoints(DEFAULT_NUMBER_OF_POINTS)
            .numberOfBeingLateInRow(DEFAULT_NUMBER_OF_BEING_LATE_IN_ROW)
            .numberOfCoursesFinished(DEFAULT_NUMBER_OF_COURSES_FINISHED)
            .numberOfBeingLateTotal(DEFAULT_NUMBER_OF_BEING_LATE_TOTAL)
            .numberOfCoursesTotalOmited(DEFAULT_NUMBER_OF_COURSES_TOTAL_OMITED)
            .numberOfCoursesOmitedInRow(DEFAULT_NUMBER_OF_COURSES_OMITED_IN_ROW);
        return userDetailsExtras;
    }

    @Before
    public void initTest() {
        userDetailsExtras = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserDetailsExtras() throws Exception {
        int databaseSizeBeforeCreate = userDetailsExtrasRepository.findAll().size();

        // Create the UserDetailsExtras
        restUserDetailsExtrasMockMvc.perform(post("/api/user-details-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDetailsExtras)))
            .andExpect(status().isCreated());

        // Validate the UserDetailsExtras in the database
        List<UserDetailsExtras> userDetailsExtrasList = userDetailsExtrasRepository.findAll();
        assertThat(userDetailsExtrasList).hasSize(databaseSizeBeforeCreate + 1);
        UserDetailsExtras testUserDetailsExtras = userDetailsExtrasList.get(userDetailsExtrasList.size() - 1);
        assertThat(testUserDetailsExtras.getNumberOfPoints()).isEqualTo(DEFAULT_NUMBER_OF_POINTS);
        assertThat(testUserDetailsExtras.getNumberOfBeingLateInRow()).isEqualTo(DEFAULT_NUMBER_OF_BEING_LATE_IN_ROW);
        assertThat(testUserDetailsExtras.getNumberOfCoursesFinished()).isEqualTo(DEFAULT_NUMBER_OF_COURSES_FINISHED);
        assertThat(testUserDetailsExtras.getNumberOfBeingLateTotal()).isEqualTo(DEFAULT_NUMBER_OF_BEING_LATE_TOTAL);
        assertThat(testUserDetailsExtras.getNumberOfCoursesTotalOmited()).isEqualTo(DEFAULT_NUMBER_OF_COURSES_TOTAL_OMITED);
        assertThat(testUserDetailsExtras.getNumberOfCoursesOmitedInRow()).isEqualTo(DEFAULT_NUMBER_OF_COURSES_OMITED_IN_ROW);
    }

    @Test
    @Transactional
    public void createUserDetailsExtrasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userDetailsExtrasRepository.findAll().size();

        // Create the UserDetailsExtras with an existing ID
        userDetailsExtras.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserDetailsExtrasMockMvc.perform(post("/api/user-details-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDetailsExtras)))
            .andExpect(status().isBadRequest());

        // Validate the UserDetailsExtras in the database
        List<UserDetailsExtras> userDetailsExtrasList = userDetailsExtrasRepository.findAll();
        assertThat(userDetailsExtrasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserDetailsExtras() throws Exception {
        // Initialize the database
        userDetailsExtrasRepository.saveAndFlush(userDetailsExtras);

        // Get all the userDetailsExtrasList
        restUserDetailsExtrasMockMvc.perform(get("/api/user-details-extras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userDetailsExtras.getId().intValue())))
            .andExpect(jsonPath("$.[*].numberOfPoints").value(hasItem(DEFAULT_NUMBER_OF_POINTS)))
            .andExpect(jsonPath("$.[*].numberOfBeingLateInRow").value(hasItem(DEFAULT_NUMBER_OF_BEING_LATE_IN_ROW)))
            .andExpect(jsonPath("$.[*].numberOfCoursesFinished").value(hasItem(DEFAULT_NUMBER_OF_COURSES_FINISHED)))
            .andExpect(jsonPath("$.[*].numberOfBeingLateTotal").value(hasItem(DEFAULT_NUMBER_OF_BEING_LATE_TOTAL)))
            .andExpect(jsonPath("$.[*].numberOfCoursesTotalOmited").value(hasItem(DEFAULT_NUMBER_OF_COURSES_TOTAL_OMITED)))
            .andExpect(jsonPath("$.[*].numberOfCoursesOmitedInRow").value(hasItem(DEFAULT_NUMBER_OF_COURSES_OMITED_IN_ROW)));
    }
    
    @Test
    @Transactional
    public void getUserDetailsExtras() throws Exception {
        // Initialize the database
        userDetailsExtrasRepository.saveAndFlush(userDetailsExtras);

        // Get the userDetailsExtras
        restUserDetailsExtrasMockMvc.perform(get("/api/user-details-extras/{id}", userDetailsExtras.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userDetailsExtras.getId().intValue()))
            .andExpect(jsonPath("$.numberOfPoints").value(DEFAULT_NUMBER_OF_POINTS))
            .andExpect(jsonPath("$.numberOfBeingLateInRow").value(DEFAULT_NUMBER_OF_BEING_LATE_IN_ROW))
            .andExpect(jsonPath("$.numberOfCoursesFinished").value(DEFAULT_NUMBER_OF_COURSES_FINISHED))
            .andExpect(jsonPath("$.numberOfBeingLateTotal").value(DEFAULT_NUMBER_OF_BEING_LATE_TOTAL))
            .andExpect(jsonPath("$.numberOfCoursesTotalOmited").value(DEFAULT_NUMBER_OF_COURSES_TOTAL_OMITED))
            .andExpect(jsonPath("$.numberOfCoursesOmitedInRow").value(DEFAULT_NUMBER_OF_COURSES_OMITED_IN_ROW));
    }

    @Test
    @Transactional
    public void getNonExistingUserDetailsExtras() throws Exception {
        // Get the userDetailsExtras
        restUserDetailsExtrasMockMvc.perform(get("/api/user-details-extras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserDetailsExtras() throws Exception {
        // Initialize the database
        userDetailsExtrasService.save(userDetailsExtras);

        int databaseSizeBeforeUpdate = userDetailsExtrasRepository.findAll().size();

        // Update the userDetailsExtras
        UserDetailsExtras updatedUserDetailsExtras = userDetailsExtrasRepository.findById(userDetailsExtras.getId()).get();
        // Disconnect from session so that the updates on updatedUserDetailsExtras are not directly saved in db
        em.detach(updatedUserDetailsExtras);
        updatedUserDetailsExtras
            .numberOfPoints(UPDATED_NUMBER_OF_POINTS)
            .numberOfBeingLateInRow(UPDATED_NUMBER_OF_BEING_LATE_IN_ROW)
            .numberOfCoursesFinished(UPDATED_NUMBER_OF_COURSES_FINISHED)
            .numberOfBeingLateTotal(UPDATED_NUMBER_OF_BEING_LATE_TOTAL)
            .numberOfCoursesTotalOmited(UPDATED_NUMBER_OF_COURSES_TOTAL_OMITED)
            .numberOfCoursesOmitedInRow(UPDATED_NUMBER_OF_COURSES_OMITED_IN_ROW);

        restUserDetailsExtrasMockMvc.perform(put("/api/user-details-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserDetailsExtras)))
            .andExpect(status().isOk());

        // Validate the UserDetailsExtras in the database
        List<UserDetailsExtras> userDetailsExtrasList = userDetailsExtrasRepository.findAll();
        assertThat(userDetailsExtrasList).hasSize(databaseSizeBeforeUpdate);
        UserDetailsExtras testUserDetailsExtras = userDetailsExtrasList.get(userDetailsExtrasList.size() - 1);
        assertThat(testUserDetailsExtras.getNumberOfPoints()).isEqualTo(UPDATED_NUMBER_OF_POINTS);
        assertThat(testUserDetailsExtras.getNumberOfBeingLateInRow()).isEqualTo(UPDATED_NUMBER_OF_BEING_LATE_IN_ROW);
        assertThat(testUserDetailsExtras.getNumberOfCoursesFinished()).isEqualTo(UPDATED_NUMBER_OF_COURSES_FINISHED);
        assertThat(testUserDetailsExtras.getNumberOfBeingLateTotal()).isEqualTo(UPDATED_NUMBER_OF_BEING_LATE_TOTAL);
        assertThat(testUserDetailsExtras.getNumberOfCoursesTotalOmited()).isEqualTo(UPDATED_NUMBER_OF_COURSES_TOTAL_OMITED);
        assertThat(testUserDetailsExtras.getNumberOfCoursesOmitedInRow()).isEqualTo(UPDATED_NUMBER_OF_COURSES_OMITED_IN_ROW);
    }

    @Test
    @Transactional
    public void updateNonExistingUserDetailsExtras() throws Exception {
        int databaseSizeBeforeUpdate = userDetailsExtrasRepository.findAll().size();

        // Create the UserDetailsExtras

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserDetailsExtrasMockMvc.perform(put("/api/user-details-extras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDetailsExtras)))
            .andExpect(status().isBadRequest());

        // Validate the UserDetailsExtras in the database
        List<UserDetailsExtras> userDetailsExtrasList = userDetailsExtrasRepository.findAll();
        assertThat(userDetailsExtrasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserDetailsExtras() throws Exception {
        // Initialize the database
        userDetailsExtrasService.save(userDetailsExtras);

        int databaseSizeBeforeDelete = userDetailsExtrasRepository.findAll().size();

        // Delete the userDetailsExtras
        restUserDetailsExtrasMockMvc.perform(delete("/api/user-details-extras/{id}", userDetailsExtras.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserDetailsExtras> userDetailsExtrasList = userDetailsExtrasRepository.findAll();
        assertThat(userDetailsExtrasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserDetailsExtras.class);
        UserDetailsExtras userDetailsExtras1 = new UserDetailsExtras();
        userDetailsExtras1.setId(1L);
        UserDetailsExtras userDetailsExtras2 = new UserDetailsExtras();
        userDetailsExtras2.setId(userDetailsExtras1.getId());
        assertThat(userDetailsExtras1).isEqualTo(userDetailsExtras2);
        userDetailsExtras2.setId(2L);
        assertThat(userDetailsExtras1).isNotEqualTo(userDetailsExtras2);
        userDetailsExtras1.setId(null);
        assertThat(userDetailsExtras1).isNotEqualTo(userDetailsExtras2);
    }
}
