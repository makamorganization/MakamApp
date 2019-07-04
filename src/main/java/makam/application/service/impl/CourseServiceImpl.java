package makam.application.service.impl;

import makam.application.domain.CourseParticipant;
import makam.application.domain.User;
import makam.application.domain.UserDetails;
import makam.application.service.CourseService;
import makam.application.domain.Course;
import makam.application.repository.CourseRepository;
import makam.application.service.UserService;
import makam.application.service.dto.CourseDTO;
import makam.application.service.dto.UserDTO;
import makam.application.service.mapper.CourseMapper;
import makam.application.web.rest.AccountResource;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.web.rest.errors.FullNumberOfParticipantsInCourseException;
import makam.application.web.rest.errors.ResourceNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Course}.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final UserService userService;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper, UserService userService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.userService = userService;
    }

    /**
     * Save a course.
     *
     * @param courseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        log.debug("Request to save Course : {}", courseDTO);
        Course course = courseMapper.toEntity(courseDTO);
        course = courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    /**
     * Get all the courses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> findAll() {
        log.debug("Request to get all Courses");
        return courseRepository.findAll().stream()
            .map(courseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the courses where Certificate is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<CourseDTO> findAllWhereCertificateIsNull() {
        log.debug("Request to get all courses where Certificate is null");
        return StreamSupport
            .stream(courseRepository.findAll().spliterator(), false)
            .filter(course -> course.getCertificate() == null)
            .map(courseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one course by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseDTO> findOne(Long id) {
        log.debug("Request to get Course : {}", id);
        return courseRepository.findById(id)
            .map(courseMapper::toDto);
    }

    /**
     * Delete the course by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Course : {}", id);
        courseRepository.deleteById(id);
    }

    @Override
    public void singUpForCourse(Long courseId) {
        log.debug("Request to assing user to course : {}", courseId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (!optionalCourse.isPresent()) {
            throw new ResourceNotFound("Course not found");
        }
        Course course = optionalCourse.get();
        int courseParticipantsCount = course.getCourseParticipants().size();
        if (courseParticipantsCount == course.getMaximumNumberOfParticipants()) {
            throw new FullNumberOfParticipantsInCourseException("Course has full number of participants");
        }
        LocalDate actualDate = LocalDate.now();
        if (actualDate.isBefore(course.getRegisterStartDate())) {
            throw new BadRequestAlertException("Course register date has not started yet", "course", "400");
        }
        Optional<User> loggedUser = userService.getUserWithAuthorities();
        if (!loggedUser.isPresent()) {
            throw new ResourceNotFound("User not found");
        }
        User currentUser = loggedUser.get();
        if (checkIfCourseHasUser(currentUser.getId(), course)) {
            throw new BadRequestAlertException("You are already signed for this course", "course", "400");
        }
        CourseParticipant courseParticipant = new CourseParticipant();
        courseParticipant.setUser(currentUser.getUserDetails());
        course = course.addCourseParticipant(courseParticipant);
        courseRepository.save(course);
    }

    @Override
    public void signOutFromCourse(Long courseId) {
        log.debug("Request to assing user to course : {}", courseId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (!optionalCourse.isPresent()) {
            throw new ResourceNotFound("Course not found");
        }
        Course course = optionalCourse.get();
        Optional<User> loggedUser = userService.getUserWithAuthorities();
        if (!loggedUser.isPresent()) {
            throw new ResourceNotFound("User not found");
        }
        User currentUser = loggedUser.get();
        Set<CourseParticipant> courseParticipants = course.getCourseParticipants();
        CourseParticipant courseParticipantToRemove = null;
        for (CourseParticipant courseParticipant : courseParticipants) {
            if (courseParticipant != null) {
                if (currentUser.getUserDetails().getId().equals(courseParticipant.getUser().getId())) {
                    courseParticipantToRemove = courseParticipant;
                }
            }
        }
        if (courseParticipantToRemove != null) {
            course = course.removeCourseParticipant(courseParticipantToRemove);
        }
        courseRepository.save(course);
    }

    @Override
    public List<CourseDTO> getCoursesForUser() {
        Optional<User> loggedUser = userService.getUserWithAuthorities();
        if (!loggedUser.isPresent()) {
            throw new ResourceNotFound("User not found");
        }
        User currentUser = loggedUser.get();
        List<Course> userCourses = new ArrayList<>();
        List<Course> allCourses = courseRepository.findAll();
        for (Course course : allCourses) {
            if (checkIfCourseHasUser(currentUser.getId(), course)) {
                userCourses.add(course);
            }
        }
        return userCourses.stream()
            .map(courseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    private boolean checkIfCourseHasUser(Long userId, Course course) {
        if (course == null || userId == null) {
            return false;
        }
        Set<CourseParticipant> courseParticipants = course.getCourseParticipants();
        for (CourseParticipant courseParticipant : courseParticipants) {
            if (courseParticipant != null) {
                UserDetails userDetails = courseParticipant.getUser();
                if (userDetails != null) {
                    if (userDetails.getUser() != null && userDetails.getUser().getId().equals(userId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
