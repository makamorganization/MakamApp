package makam.application.web.rest;

import makam.application.MakamApp;

import makam.application.domain.CourseParticipant;
import makam.application.repository.CourseParticipantRepository;
import makam.application.service.CourseParticipantService;
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
 * Test class for the CourseParticipantResource REST controller.
 *
 * @see CourseParticipantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakamApp.class)
public class CourseParticipantResourceIntTest {

    private static final Boolean DEFAULT_IS_USER_PRESENT = false;
    private static final Boolean UPDATED_IS_USER_PRESENT = true;

    private static final Boolean DEFAULT_IS_USER_LATE = false;
    private static final Boolean UPDATED_IS_USER_LATE = true;

    private static final Boolean DEFAULT_CAN_CANCEL_COURSE_ATTENDANCE = false;
    private static final Boolean UPDATED_CAN_CANCEL_COURSE_ATTENDANCE = true;

    @Autowired
    private CourseParticipantRepository courseParticipantRepository;

    @Autowired
    private CourseParticipantService courseParticipantService;

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

    private MockMvc restCourseParticipantMockMvc;

    private CourseParticipant courseParticipant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseParticipantResource courseParticipantResource = new CourseParticipantResource(courseParticipantService);
        this.restCourseParticipantMockMvc = MockMvcBuilders.standaloneSetup(courseParticipantResource)
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
    public static CourseParticipant createEntity(EntityManager em) {
        CourseParticipant courseParticipant = new CourseParticipant()
            .isUserPresent(DEFAULT_IS_USER_PRESENT)
            .isUserLate(DEFAULT_IS_USER_LATE)
            .canCancelCourseAttendance(DEFAULT_CAN_CANCEL_COURSE_ATTENDANCE);
        return courseParticipant;
    }

    @Before
    public void initTest() {
        courseParticipant = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseParticipant() throws Exception {
        int databaseSizeBeforeCreate = courseParticipantRepository.findAll().size();

        // Create the CourseParticipant
        restCourseParticipantMockMvc.perform(post("/api/course-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseParticipant)))
            .andExpect(status().isCreated());

        // Validate the CourseParticipant in the database
        List<CourseParticipant> courseParticipantList = courseParticipantRepository.findAll();
        assertThat(courseParticipantList).hasSize(databaseSizeBeforeCreate + 1);
        CourseParticipant testCourseParticipant = courseParticipantList.get(courseParticipantList.size() - 1);
        assertThat(testCourseParticipant.isIsUserPresent()).isEqualTo(DEFAULT_IS_USER_PRESENT);
        assertThat(testCourseParticipant.isIsUserLate()).isEqualTo(DEFAULT_IS_USER_LATE);
        assertThat(testCourseParticipant.isCanCancelCourseAttendance()).isEqualTo(DEFAULT_CAN_CANCEL_COURSE_ATTENDANCE);
    }

    @Test
    @Transactional
    public void createCourseParticipantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseParticipantRepository.findAll().size();

        // Create the CourseParticipant with an existing ID
        courseParticipant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseParticipantMockMvc.perform(post("/api/course-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseParticipant)))
            .andExpect(status().isBadRequest());

        // Validate the CourseParticipant in the database
        List<CourseParticipant> courseParticipantList = courseParticipantRepository.findAll();
        assertThat(courseParticipantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCourseParticipants() throws Exception {
        // Initialize the database
        courseParticipantRepository.saveAndFlush(courseParticipant);

        // Get all the courseParticipantList
        restCourseParticipantMockMvc.perform(get("/api/course-participants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseParticipant.getId().intValue())))
            .andExpect(jsonPath("$.[*].isUserPresent").value(hasItem(DEFAULT_IS_USER_PRESENT.booleanValue())))
            .andExpect(jsonPath("$.[*].isUserLate").value(hasItem(DEFAULT_IS_USER_LATE.booleanValue())))
            .andExpect(jsonPath("$.[*].canCancelCourseAttendance").value(hasItem(DEFAULT_CAN_CANCEL_COURSE_ATTENDANCE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCourseParticipant() throws Exception {
        // Initialize the database
        courseParticipantRepository.saveAndFlush(courseParticipant);

        // Get the courseParticipant
        restCourseParticipantMockMvc.perform(get("/api/course-participants/{id}", courseParticipant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseParticipant.getId().intValue()))
            .andExpect(jsonPath("$.isUserPresent").value(DEFAULT_IS_USER_PRESENT.booleanValue()))
            .andExpect(jsonPath("$.isUserLate").value(DEFAULT_IS_USER_LATE.booleanValue()))
            .andExpect(jsonPath("$.canCancelCourseAttendance").value(DEFAULT_CAN_CANCEL_COURSE_ATTENDANCE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseParticipant() throws Exception {
        // Get the courseParticipant
        restCourseParticipantMockMvc.perform(get("/api/course-participants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseParticipant() throws Exception {
        // Initialize the database
        courseParticipantService.save(courseParticipant);

        int databaseSizeBeforeUpdate = courseParticipantRepository.findAll().size();

        // Update the courseParticipant
        CourseParticipant updatedCourseParticipant = courseParticipantRepository.findById(courseParticipant.getId()).get();
        // Disconnect from session so that the updates on updatedCourseParticipant are not directly saved in db
        em.detach(updatedCourseParticipant);
        updatedCourseParticipant
            .isUserPresent(UPDATED_IS_USER_PRESENT)
            .isUserLate(UPDATED_IS_USER_LATE)
            .canCancelCourseAttendance(UPDATED_CAN_CANCEL_COURSE_ATTENDANCE);

        restCourseParticipantMockMvc.perform(put("/api/course-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseParticipant)))
            .andExpect(status().isOk());

        // Validate the CourseParticipant in the database
        List<CourseParticipant> courseParticipantList = courseParticipantRepository.findAll();
        assertThat(courseParticipantList).hasSize(databaseSizeBeforeUpdate);
        CourseParticipant testCourseParticipant = courseParticipantList.get(courseParticipantList.size() - 1);
        assertThat(testCourseParticipant.isIsUserPresent()).isEqualTo(UPDATED_IS_USER_PRESENT);
        assertThat(testCourseParticipant.isIsUserLate()).isEqualTo(UPDATED_IS_USER_LATE);
        assertThat(testCourseParticipant.isCanCancelCourseAttendance()).isEqualTo(UPDATED_CAN_CANCEL_COURSE_ATTENDANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseParticipant() throws Exception {
        int databaseSizeBeforeUpdate = courseParticipantRepository.findAll().size();

        // Create the CourseParticipant

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseParticipantMockMvc.perform(put("/api/course-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseParticipant)))
            .andExpect(status().isBadRequest());

        // Validate the CourseParticipant in the database
        List<CourseParticipant> courseParticipantList = courseParticipantRepository.findAll();
        assertThat(courseParticipantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCourseParticipant() throws Exception {
        // Initialize the database
        courseParticipantService.save(courseParticipant);

        int databaseSizeBeforeDelete = courseParticipantRepository.findAll().size();

        // Delete the courseParticipant
        restCourseParticipantMockMvc.perform(delete("/api/course-participants/{id}", courseParticipant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseParticipant> courseParticipantList = courseParticipantRepository.findAll();
        assertThat(courseParticipantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseParticipant.class);
        CourseParticipant courseParticipant1 = new CourseParticipant();
        courseParticipant1.setId(1L);
        CourseParticipant courseParticipant2 = new CourseParticipant();
        courseParticipant2.setId(courseParticipant1.getId());
        assertThat(courseParticipant1).isEqualTo(courseParticipant2);
        courseParticipant2.setId(2L);
        assertThat(courseParticipant1).isNotEqualTo(courseParticipant2);
        courseParticipant1.setId(null);
        assertThat(courseParticipant1).isNotEqualTo(courseParticipant2);
    }
}
