package makam.application.web.rest;
import makam.application.domain.FacultyDictionary;
import makam.application.service.FacultyDictionaryService;
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
 * REST controller for managing FacultyDictionary.
 */
@RestController
@RequestMapping("/api")
public class FacultyDictionaryResource {

    private final Logger log = LoggerFactory.getLogger(FacultyDictionaryResource.class);

    private static final String ENTITY_NAME = "facultyDictionary";

    private final FacultyDictionaryService facultyDictionaryService;

    public FacultyDictionaryResource(FacultyDictionaryService facultyDictionaryService) {
        this.facultyDictionaryService = facultyDictionaryService;
    }

    /**
     * POST  /faculty-dictionaries : Create a new facultyDictionary.
     *
     * @param facultyDictionary the facultyDictionary to create
     * @return the ResponseEntity with status 201 (Created) and with body the new facultyDictionary, or with status 400 (Bad Request) if the facultyDictionary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/faculty-dictionaries")
    public ResponseEntity<FacultyDictionary> createFacultyDictionary(@RequestBody FacultyDictionary facultyDictionary) throws URISyntaxException {
        log.debug("REST request to save FacultyDictionary : {}", facultyDictionary);
        if (facultyDictionary.getId() != null) {
            throw new BadRequestAlertException("A new facultyDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FacultyDictionary result = facultyDictionaryService.save(facultyDictionary);
        return ResponseEntity.created(new URI("/api/faculty-dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /faculty-dictionaries : Updates an existing facultyDictionary.
     *
     * @param facultyDictionary the facultyDictionary to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated facultyDictionary,
     * or with status 400 (Bad Request) if the facultyDictionary is not valid,
     * or with status 500 (Internal Server Error) if the facultyDictionary couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/faculty-dictionaries")
    public ResponseEntity<FacultyDictionary> updateFacultyDictionary(@RequestBody FacultyDictionary facultyDictionary) throws URISyntaxException {
        log.debug("REST request to update FacultyDictionary : {}", facultyDictionary);
        if (facultyDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FacultyDictionary result = facultyDictionaryService.save(facultyDictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, facultyDictionary.getId().toString()))
            .body(result);
    }

    /**
     * GET  /faculty-dictionaries : get all the facultyDictionaries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of facultyDictionaries in body
     */
    @GetMapping("/faculty-dictionaries")
    public List<FacultyDictionary> getAllFacultyDictionaries() {
        log.debug("REST request to get all FacultyDictionaries");
        return facultyDictionaryService.findAll();
    }

    /**
     * GET  /faculty-dictionaries/:id : get the "id" facultyDictionary.
     *
     * @param id the id of the facultyDictionary to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the facultyDictionary, or with status 404 (Not Found)
     */
    @GetMapping("/faculty-dictionaries/{id}")
    public ResponseEntity<FacultyDictionary> getFacultyDictionary(@PathVariable Long id) {
        log.debug("REST request to get FacultyDictionary : {}", id);
        Optional<FacultyDictionary> facultyDictionary = facultyDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(facultyDictionary);
    }

    /**
     * DELETE  /faculty-dictionaries/:id : delete the "id" facultyDictionary.
     *
     * @param id the id of the facultyDictionary to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/faculty-dictionaries/{id}")
    public ResponseEntity<Void> deleteFacultyDictionary(@PathVariable Long id) {
        log.debug("REST request to delete FacultyDictionary : {}", id);
        facultyDictionaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
