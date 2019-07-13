package makam.application.web.rest;

import makam.application.MakamApp;
import makam.application.domain.Course;
import makam.application.repository.CourseRepository;
import makam.application.service.CourseService;
import makam.application.service.UserService;
import makam.application.service.dto.CourseDTO;
import makam.application.service.mapper.CourseMapper;
import makam.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
 * Integration tests for the {@Link CourseResource} REST controller.
 */
@SpringBootTest(classes = MakamApp.class)
public class CourseResourceIT {

    private static final Long MILISECONDS_IN_HOUR = 360000L;
    private static final Long CURRENT_TIME = System.currentTimeMillis();
    
    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_COURSE_START_DATE = CURRENT_TIME;
    private static final Long UPDATED_COURSE_START_DATE = CURRENT_TIME + MILISECONDS_IN_HOUR;

    private static final Long DEFAULT_COURSE_END_DATE = CURRENT_TIME;
    private static final Long UPDATED_COURSE_END_DATE = CURRENT_TIME + MILISECONDS_IN_HOUR;

    private static final Long DEFAULT_REGISTER_START_DATE = CURRENT_TIME;
    private static final Long UPDATED_REGISTER_START_DATE = CURRENT_TIME + MILISECONDS_IN_HOUR;

    private static final Long DEFAULT_REGISTER_END_DATE = CURRENT_TIME;
    private static final Long UPDATED_REGISTER_END_DATE = CURRENT_TIME + MILISECONDS_IN_HOUR;

    private static final Long DEFAULT_DURATION = 1L;
    private static final Long UPDATED_DURATION = 2L;

    private static final Integer DEFAULT_MAXIMUM_NUMBER_OF_PARTICIPANTS = 1;
    private static final Integer UPDATED_MAXIMUM_NUMBER_OF_PARTICIPANTS = 2;

    private static final Integer DEFAULT_MINIMAL_NUMBER_OF_PARTICIPANTS = 1;
    private static final Integer UPDATED_MINIMAL_NUMBER_OF_PARTICIPANTS = 2;

    private static final String DEFAULT_LECTURER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LECTURER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LECTURER_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_LECTURER_SURNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_POINT_PER_COURSE = 1;
    private static final Integer UPDATED_POINT_PER_COURSE = 2;

    private static final Boolean DEFAULT_IS_VISIBLE_IN_APP = false;
    private static final Boolean UPDATED_IS_VISIBLE_IN_APP = true;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

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

    private MockMvc restCourseMockMvc;

    private Course course;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseResource courseResource = new CourseResource(courseService, userService);
        this.restCourseMockMvc = MockMvcBuilders.standaloneSetup(courseResource)
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
    public static Course createEntity(EntityManager em) {
        Course course = new Course()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .courseStartDate(DEFAULT_COURSE_START_DATE)
            .courseEndDate(DEFAULT_COURSE_END_DATE)
            .registerStartDate(DEFAULT_REGISTER_START_DATE)
            .registerEndDate(DEFAULT_REGISTER_END_DATE)
            .duration(DEFAULT_DURATION)
            .maximumNumberOfParticipants(DEFAULT_MAXIMUM_NUMBER_OF_PARTICIPANTS)
            .minimalNumberOfParticipants(DEFAULT_MINIMAL_NUMBER_OF_PARTICIPANTS)
            .lecturerName(DEFAULT_LECTURER_NAME)
            .lecturerSurname(DEFAULT_LECTURER_SURNAME)
            .pointPerCourse(DEFAULT_POINT_PER_COURSE)
            .isVisibleInApp(DEFAULT_IS_VISIBLE_IN_APP);
        return course;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Course createUpdatedEntity(EntityManager em) {
        Course course = new Course()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .courseStartDate(UPDATED_COURSE_START_DATE)
            .courseEndDate(UPDATED_COURSE_END_DATE)
            .registerStartDate(UPDATED_REGISTER_START_DATE)
            .registerEndDate(UPDATED_REGISTER_END_DATE)
            .duration(UPDATED_DURATION)
            .maximumNumberOfParticipants(UPDATED_MAXIMUM_NUMBER_OF_PARTICIPANTS)
            .minimalNumberOfParticipants(UPDATED_MINIMAL_NUMBER_OF_PARTICIPANTS)
            .lecturerName(UPDATED_LECTURER_NAME)
            .lecturerSurname(UPDATED_LECTURER_SURNAME)
            .pointPerCourse(UPDATED_POINT_PER_COURSE)
            .isVisibleInApp(UPDATED_IS_VISIBLE_IN_APP);
        return course;
    }

    @BeforeEach
    public void initTest() {
        course = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourse() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);
        restCourseMockMvc.perform(post("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isCreated());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate + 1);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCourse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCourse.getCourseStartDate()).isEqualTo(DEFAULT_COURSE_START_DATE);
        assertThat(testCourse.getCourseEndDate()).isEqualTo(DEFAULT_COURSE_END_DATE);
        assertThat(testCourse.getRegisterStartDate()).isEqualTo(DEFAULT_REGISTER_START_DATE);
        assertThat(testCourse.getRegisterEndDate()).isEqualTo(DEFAULT_REGISTER_END_DATE);
        assertThat(testCourse.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testCourse.getMaximumNumberOfParticipants()).isEqualTo(DEFAULT_MAXIMUM_NUMBER_OF_PARTICIPANTS);
        assertThat(testCourse.getMinimalNumberOfParticipants()).isEqualTo(DEFAULT_MINIMAL_NUMBER_OF_PARTICIPANTS);
        assertThat(testCourse.getLecturerName()).isEqualTo(DEFAULT_LECTURER_NAME);
        assertThat(testCourse.getLecturerSurname()).isEqualTo(DEFAULT_LECTURER_SURNAME);
        assertThat(testCourse.getPointPerCourse()).isEqualTo(DEFAULT_POINT_PER_COURSE);
        assertThat(testCourse.isIsVisibleInApp()).isEqualTo(DEFAULT_IS_VISIBLE_IN_APP);
    }

    @Test
    @Transactional
    public void createCourseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // Create the Course with an existing ID
        course.setId(1L);
        CourseDTO courseDTO = courseMapper.toDto(course);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseMockMvc.perform(post("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCourses() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get all the courseList
        restCourseMockMvc.perform(get("/api/courses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(course.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].courseStartDate").value(hasItem(DEFAULT_COURSE_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].courseEndDate").value(hasItem(DEFAULT_COURSE_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerStartDate").value(hasItem(DEFAULT_REGISTER_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].registerEndDate").value(hasItem(DEFAULT_REGISTER_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.intValue())))
            .andExpect(jsonPath("$.[*].maximumNumberOfParticipants").value(hasItem(DEFAULT_MAXIMUM_NUMBER_OF_PARTICIPANTS)))
            .andExpect(jsonPath("$.[*].minimalNumberOfParticipants").value(hasItem(DEFAULT_MINIMAL_NUMBER_OF_PARTICIPANTS)))
            .andExpect(jsonPath("$.[*].lecturerName").value(hasItem(DEFAULT_LECTURER_NAME.toString())))
            .andExpect(jsonPath("$.[*].lecturerSurname").value(hasItem(DEFAULT_LECTURER_SURNAME.toString())))
            .andExpect(jsonPath("$.[*].pointPerCourse").value(hasItem(DEFAULT_POINT_PER_COURSE)))
            .andExpect(jsonPath("$.[*].isVisibleInApp").value(hasItem(DEFAULT_IS_VISIBLE_IN_APP.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get the course
        restCourseMockMvc.perform(get("/api/courses/{id}", course.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(course.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.courseStartDate").value(DEFAULT_COURSE_START_DATE.toString()))
            .andExpect(jsonPath("$.courseEndDate").value(DEFAULT_COURSE_END_DATE.toString()))
            .andExpect(jsonPath("$.registerStartDate").value(DEFAULT_REGISTER_START_DATE.toString()))
            .andExpect(jsonPath("$.registerEndDate").value(DEFAULT_REGISTER_END_DATE.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.intValue()))
            .andExpect(jsonPath("$.maximumNumberOfParticipants").value(DEFAULT_MAXIMUM_NUMBER_OF_PARTICIPANTS))
            .andExpect(jsonPath("$.minimalNumberOfParticipants").value(DEFAULT_MINIMAL_NUMBER_OF_PARTICIPANTS))
            .andExpect(jsonPath("$.lecturerName").value(DEFAULT_LECTURER_NAME.toString()))
            .andExpect(jsonPath("$.lecturerSurname").value(DEFAULT_LECTURER_SURNAME.toString()))
            .andExpect(jsonPath("$.pointPerCourse").value(DEFAULT_POINT_PER_COURSE))
            .andExpect(jsonPath("$.isVisibleInApp").value(DEFAULT_IS_VISIBLE_IN_APP.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCourse() throws Exception {
        // Get the course
        restCourseMockMvc.perform(get("/api/courses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course
        Course updatedCourse = courseRepository.findById(course.getId()).get();
        // Disconnect from session so that the updates on updatedCourse are not directly saved in db
        em.detach(updatedCourse);
        updatedCourse
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .courseStartDate(UPDATED_COURSE_START_DATE)
            .courseEndDate(UPDATED_COURSE_END_DATE)
            .registerStartDate(UPDATED_REGISTER_START_DATE)
            .registerEndDate(UPDATED_REGISTER_END_DATE)
            .duration(UPDATED_DURATION)
            .maximumNumberOfParticipants(UPDATED_MAXIMUM_NUMBER_OF_PARTICIPANTS)
            .minimalNumberOfParticipants(UPDATED_MINIMAL_NUMBER_OF_PARTICIPANTS)
            .lecturerName(UPDATED_LECTURER_NAME)
            .lecturerSurname(UPDATED_LECTURER_SURNAME)
            .pointPerCourse(UPDATED_POINT_PER_COURSE)
            .isVisibleInApp(UPDATED_IS_VISIBLE_IN_APP);
        CourseDTO courseDTO = courseMapper.toDto(updatedCourse);

        restCourseMockMvc.perform(put("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCourse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourse.getCourseStartDate()).isEqualTo(UPDATED_COURSE_START_DATE);
        assertThat(testCourse.getCourseEndDate()).isEqualTo(UPDATED_COURSE_END_DATE);
        assertThat(testCourse.getRegisterStartDate()).isEqualTo(UPDATED_REGISTER_START_DATE);
        assertThat(testCourse.getRegisterEndDate()).isEqualTo(UPDATED_REGISTER_END_DATE);
        assertThat(testCourse.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testCourse.getMaximumNumberOfParticipants()).isEqualTo(UPDATED_MAXIMUM_NUMBER_OF_PARTICIPANTS);
        assertThat(testCourse.getMinimalNumberOfParticipants()).isEqualTo(UPDATED_MINIMAL_NUMBER_OF_PARTICIPANTS);
        assertThat(testCourse.getLecturerName()).isEqualTo(UPDATED_LECTURER_NAME);
        assertThat(testCourse.getLecturerSurname()).isEqualTo(UPDATED_LECTURER_SURNAME);
        assertThat(testCourse.getPointPerCourse()).isEqualTo(UPDATED_POINT_PER_COURSE);
        assertThat(testCourse.isIsVisibleInApp()).isEqualTo(UPDATED_IS_VISIBLE_IN_APP);
    }

    @Test
    @Transactional
    public void updateNonExistingCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseMockMvc.perform(put("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeDelete = courseRepository.findAll().size();

        // Delete the course
        restCourseMockMvc.perform(delete("/api/courses/{id}", course.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Course.class);
        Course course1 = new Course();
        course1.setId(1L);
        Course course2 = new Course();
        course2.setId(course1.getId());
        assertThat(course1).isEqualTo(course2);
        course2.setId(2L);
        assertThat(course1).isNotEqualTo(course2);
        course1.setId(null);
        assertThat(course1).isNotEqualTo(course2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseDTO.class);
        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setId(1L);
        CourseDTO courseDTO2 = new CourseDTO();
        assertThat(courseDTO1).isNotEqualTo(courseDTO2);
        courseDTO2.setId(courseDTO1.getId());
        assertThat(courseDTO1).isEqualTo(courseDTO2);
        courseDTO2.setId(2L);
        assertThat(courseDTO1).isNotEqualTo(courseDTO2);
        courseDTO1.setId(null);
        assertThat(courseDTO1).isNotEqualTo(courseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(courseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(courseMapper.fromId(null)).isNull();
    }
}
