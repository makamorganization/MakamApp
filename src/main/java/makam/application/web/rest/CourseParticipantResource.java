package makam.application.web.rest;

import makam.application.service.CourseParticipantService;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.service.dto.CourseParticipantDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link makam.application.domain.CourseParticipant}.
 */
@RestController
@RequestMapping("/api")
public class CourseParticipantResource {

    private final Logger log = LoggerFactory.getLogger(CourseParticipantResource.class);

    private static final String ENTITY_NAME = "courseParticipant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CourseParticipantService courseParticipantService;

    public CourseParticipantResource(CourseParticipantService courseParticipantService) {
        this.courseParticipantService = courseParticipantService;
    }

    /**
     * {@code POST  /course-participants} : Create a new courseParticipant.
     *
     * @param courseParticipantDTO the courseParticipantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new courseParticipantDTO, or with status {@code 400 (Bad Request)} if the courseParticipant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/course-participants")
    public ResponseEntity<CourseParticipantDTO> createCourseParticipant(@RequestBody CourseParticipantDTO courseParticipantDTO) throws URISyntaxException {
        log.debug("REST request to save CourseParticipant : {}", courseParticipantDTO);
        if (courseParticipantDTO.getId() != null) {
            throw new BadRequestAlertException("A new courseParticipant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourseParticipantDTO result = courseParticipantService.save(courseParticipantDTO);
        return ResponseEntity.created(new URI("/api/course-participants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /course-participants} : Updates an existing courseParticipant.
     *
     * @param courseParticipantDTO the courseParticipantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courseParticipantDTO,
     * or with status {@code 400 (Bad Request)} if the courseParticipantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the courseParticipantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/course-participants")
    public ResponseEntity<CourseParticipantDTO> updateCourseParticipant(@RequestBody CourseParticipantDTO courseParticipantDTO) throws URISyntaxException {
        log.debug("REST request to update CourseParticipant : {}", courseParticipantDTO);
        if (courseParticipantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CourseParticipantDTO result = courseParticipantService.save(courseParticipantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, courseParticipantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /course-participants} : get all the courseParticipants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courseParticipants in body.
     */
    @GetMapping("/course-participants")
    public List<CourseParticipantDTO> getAllCourseParticipants() {
        log.debug("REST request to get all CourseParticipants");
        return courseParticipantService.findAll();
    }

    /**
     * {@code GET  /course-participants/:id} : get the "id" courseParticipant.
     *
     * @param id the id of the courseParticipantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the courseParticipantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-participants/{id}")
    public ResponseEntity<CourseParticipantDTO> getCourseParticipant(@PathVariable Long id) {
        log.debug("REST request to get CourseParticipant : {}", id);
        Optional<CourseParticipantDTO> courseParticipantDTO = courseParticipantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseParticipantDTO);
    }

    /**
     * {@code DELETE  /course-participants/:id} : delete the "id" courseParticipant.
     *
     * @param id the id of the courseParticipantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/course-participants/{id}")
    public ResponseEntity<Void> deleteCourseParticipant(@PathVariable Long id) {
        log.debug("REST request to delete CourseParticipant : {}", id);
        courseParticipantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
