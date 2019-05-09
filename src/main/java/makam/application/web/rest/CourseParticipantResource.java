package makam.application.web.rest;
import makam.application.domain.CourseParticipant;
import makam.application.repository.CourseParticipantRepository;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CourseParticipant.
 */
@RestController
@RequestMapping("/api")
public class CourseParticipantResource {

    private final Logger log = LoggerFactory.getLogger(CourseParticipantResource.class);

    private static final String ENTITY_NAME = "courseParticipant";

    private final CourseParticipantRepository courseParticipantRepository;

    public CourseParticipantResource(CourseParticipantRepository courseParticipantRepository) {
        this.courseParticipantRepository = courseParticipantRepository;
    }

    /**
     * POST  /course-participants : Create a new courseParticipant.
     *
     * @param courseParticipant the courseParticipant to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseParticipant, or with status 400 (Bad Request) if the courseParticipant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-participants")
    public ResponseEntity<CourseParticipant> createCourseParticipant(@RequestBody CourseParticipant courseParticipant) throws URISyntaxException {
        log.debug("REST request to save CourseParticipant : {}", courseParticipant);
        if (courseParticipant.getId() != null) {
            throw new BadRequestAlertException("A new courseParticipant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourseParticipant result = courseParticipantRepository.save(courseParticipant);
        return ResponseEntity.created(new URI("/api/course-participants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-participants : Updates an existing courseParticipant.
     *
     * @param courseParticipant the courseParticipant to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseParticipant,
     * or with status 400 (Bad Request) if the courseParticipant is not valid,
     * or with status 500 (Internal Server Error) if the courseParticipant couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-participants")
    public ResponseEntity<CourseParticipant> updateCourseParticipant(@RequestBody CourseParticipant courseParticipant) throws URISyntaxException {
        log.debug("REST request to update CourseParticipant : {}", courseParticipant);
        if (courseParticipant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CourseParticipant result = courseParticipantRepository.save(courseParticipant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseParticipant.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-participants : get all the courseParticipants.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of courseParticipants in body
     */
    @GetMapping("/course-participants")
    public List<CourseParticipant> getAllCourseParticipants() {
        log.debug("REST request to get all CourseParticipants");
        return courseParticipantRepository.findAll();
    }

    /**
     * GET  /course-participants/:id : get the "id" courseParticipant.
     *
     * @param id the id of the courseParticipant to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseParticipant, or with status 404 (Not Found)
     */
    @GetMapping("/course-participants/{id}")
    public ResponseEntity<CourseParticipant> getCourseParticipant(@PathVariable Long id) {
        log.debug("REST request to get CourseParticipant : {}", id);
        Optional<CourseParticipant> courseParticipant = courseParticipantRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(courseParticipant);
    }

    /**
     * DELETE  /course-participants/:id : delete the "id" courseParticipant.
     *
     * @param id the id of the courseParticipant to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-participants/{id}")
    public ResponseEntity<Void> deleteCourseParticipant(@PathVariable Long id) {
        log.debug("REST request to delete CourseParticipant : {}", id);
        courseParticipantRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
