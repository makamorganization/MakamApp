package makam.application.web.rest;

import makam.application.MakamApp;

import makam.application.domain.UserDetails;
import makam.application.repository.UserDetailsRepository;
import makam.application.service.UserDetailsService;
import makam.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static makam.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserDetailsResource REST controller.
 *
 * @see UserDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakamApp.class)
public class UserDetailsResourceIntTest {

    private static final Integer DEFAULT_STUDENT_CARD_NUMBER = 1;
    private static final Integer UPDATED_STUDENT_CARD_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_STUDY_YEAR = 1;
    private static final Integer UPDATED_STUDY_YEAR = 2;

    private static final String DEFAULT_FACULTY = "AAAAAAAAAA";
    private static final String UPDATED_FACULTY = "BBBBBBBBBB";

    private static final String DEFAULT_FIELD_OF_STUDY = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_OF_STUDY = "BBBBBBBBBB";

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private UserDetailsRepository userDetailsRepositoryMock;

    @Mock
    private UserDetailsService userDetailsServiceMock;

    @Autowired
    private UserDetailsService userDetailsService;

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

    private MockMvc restUserDetailsMockMvc;

    private UserDetails userDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserDetailsResource userDetailsResource = new UserDetailsResource(userDetailsService);
        this.restUserDetailsMockMvc = MockMvcBuilders.standaloneSetup(userDetailsResource)
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
    public static UserDetails createEntity(EntityManager em) {
        UserDetails userDetails = new UserDetails()
            .studentCardNumber(DEFAULT_STUDENT_CARD_NUMBER)
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .telephoneNumber(DEFAULT_TELEPHONE_NUMBER)
            .studyYear(DEFAULT_STUDY_YEAR)
            .faculty(DEFAULT_FACULTY)
            .fieldOfStudy(DEFAULT_FIELD_OF_STUDY);
        return userDetails;
    }

    @Before
    public void initTest() {
        userDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserDetails() throws Exception {
        int databaseSizeBeforeCreate = userDetailsRepository.findAll().size();

        // Create the UserDetails
        restUserDetailsMockMvc.perform(post("/api/user-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDetails)))
            .andExpect(status().isCreated());

        // Validate the UserDetails in the database
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        assertThat(userDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        UserDetails testUserDetails = userDetailsList.get(userDetailsList.size() - 1);
        assertThat(testUserDetails.getStudentCardNumber()).isEqualTo(DEFAULT_STUDENT_CARD_NUMBER);
        assertThat(testUserDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserDetails.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testUserDetails.getTelephoneNumber()).isEqualTo(DEFAULT_TELEPHONE_NUMBER);
        assertThat(testUserDetails.getStudyYear()).isEqualTo(DEFAULT_STUDY_YEAR);
        assertThat(testUserDetails.getFaculty()).isEqualTo(DEFAULT_FACULTY);
        assertThat(testUserDetails.getFieldOfStudy()).isEqualTo(DEFAULT_FIELD_OF_STUDY);
    }

    @Test
    @Transactional
    public void createUserDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userDetailsRepository.findAll().size();

        // Create the UserDetails with an existing ID
        userDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserDetailsMockMvc.perform(post("/api/user-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserDetails in the database
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        assertThat(userDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserDetails() throws Exception {
        // Initialize the database
        userDetailsRepository.saveAndFlush(userDetails);

        // Get all the userDetailsList
        restUserDetailsMockMvc.perform(get("/api/user-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentCardNumber").value(hasItem(DEFAULT_STUDENT_CARD_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME.toString())))
            .andExpect(jsonPath("$.[*].telephoneNumber").value(hasItem(DEFAULT_TELEPHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].studyYear").value(hasItem(DEFAULT_STUDY_YEAR)))
            .andExpect(jsonPath("$.[*].faculty").value(hasItem(DEFAULT_FACULTY.toString())))
            .andExpect(jsonPath("$.[*].fieldOfStudy").value(hasItem(DEFAULT_FIELD_OF_STUDY.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllUserDetailsWithEagerRelationshipsIsEnabled() throws Exception {
        UserDetailsResource userDetailsResource = new UserDetailsResource(userDetailsServiceMock);
        when(userDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restUserDetailsMockMvc = MockMvcBuilders.standaloneSetup(userDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restUserDetailsMockMvc.perform(get("/api/user-details?eagerload=true"))
        .andExpect(status().isOk());

        verify(userDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllUserDetailsWithEagerRelationshipsIsNotEnabled() throws Exception {
        UserDetailsResource userDetailsResource = new UserDetailsResource(userDetailsServiceMock);
            when(userDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restUserDetailsMockMvc = MockMvcBuilders.standaloneSetup(userDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restUserDetailsMockMvc.perform(get("/api/user-details?eagerload=true"))
        .andExpect(status().isOk());

            verify(userDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getUserDetails() throws Exception {
        // Initialize the database
        userDetailsRepository.saveAndFlush(userDetails);

        // Get the userDetails
        restUserDetailsMockMvc.perform(get("/api/user-details/{id}", userDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userDetails.getId().intValue()))
            .andExpect(jsonPath("$.studentCardNumber").value(DEFAULT_STUDENT_CARD_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME.toString()))
            .andExpect(jsonPath("$.telephoneNumber").value(DEFAULT_TELEPHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.studyYear").value(DEFAULT_STUDY_YEAR))
            .andExpect(jsonPath("$.faculty").value(DEFAULT_FACULTY.toString()))
            .andExpect(jsonPath("$.fieldOfStudy").value(DEFAULT_FIELD_OF_STUDY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserDetails() throws Exception {
        // Get the userDetails
        restUserDetailsMockMvc.perform(get("/api/user-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserDetails() throws Exception {
        // Initialize the database
        userDetailsService.save(userDetails);

        int databaseSizeBeforeUpdate = userDetailsRepository.findAll().size();

        // Update the userDetails
        UserDetails updatedUserDetails = userDetailsRepository.findById(userDetails.getId()).get();
        // Disconnect from session so that the updates on updatedUserDetails are not directly saved in db
        em.detach(updatedUserDetails);
        updatedUserDetails
            .studentCardNumber(UPDATED_STUDENT_CARD_NUMBER)
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .telephoneNumber(UPDATED_TELEPHONE_NUMBER)
            .studyYear(UPDATED_STUDY_YEAR)
            .faculty(UPDATED_FACULTY)
            .fieldOfStudy(UPDATED_FIELD_OF_STUDY);

        restUserDetailsMockMvc.perform(put("/api/user-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserDetails)))
            .andExpect(status().isOk());

        // Validate the UserDetails in the database
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        assertThat(userDetailsList).hasSize(databaseSizeBeforeUpdate);
        UserDetails testUserDetails = userDetailsList.get(userDetailsList.size() - 1);
        assertThat(testUserDetails.getStudentCardNumber()).isEqualTo(UPDATED_STUDENT_CARD_NUMBER);
        assertThat(testUserDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserDetails.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testUserDetails.getTelephoneNumber()).isEqualTo(UPDATED_TELEPHONE_NUMBER);
        assertThat(testUserDetails.getStudyYear()).isEqualTo(UPDATED_STUDY_YEAR);
        assertThat(testUserDetails.getFaculty()).isEqualTo(UPDATED_FACULTY);
        assertThat(testUserDetails.getFieldOfStudy()).isEqualTo(UPDATED_FIELD_OF_STUDY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserDetails() throws Exception {
        int databaseSizeBeforeUpdate = userDetailsRepository.findAll().size();

        // Create the UserDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserDetailsMockMvc.perform(put("/api/user-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserDetails in the database
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        assertThat(userDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserDetails() throws Exception {
        // Initialize the database
        userDetailsService.save(userDetails);

        int databaseSizeBeforeDelete = userDetailsRepository.findAll().size();

        // Delete the userDetails
        restUserDetailsMockMvc.perform(delete("/api/user-details/{id}", userDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserDetails> userDetailsList = userDetailsRepository.findAll();
        assertThat(userDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserDetails.class);
        UserDetails userDetails1 = new UserDetails();
        userDetails1.setId(1L);
        UserDetails userDetails2 = new UserDetails();
        userDetails2.setId(userDetails1.getId());
        assertThat(userDetails1).isEqualTo(userDetails2);
        userDetails2.setId(2L);
        assertThat(userDetails1).isNotEqualTo(userDetails2);
        userDetails1.setId(null);
        assertThat(userDetails1).isNotEqualTo(userDetails2);
    }
}
