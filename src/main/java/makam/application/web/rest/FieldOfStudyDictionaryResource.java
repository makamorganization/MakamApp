package makam.application.web.rest;
import makam.application.domain.FieldOfStudyDictionary;
import makam.application.service.FieldOfStudyDictionaryService;
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
 * REST controller for managing FieldOfStudyDictionary.
 */
@RestController
@RequestMapping("/api")
public class FieldOfStudyDictionaryResource {

    private final Logger log = LoggerFactory.getLogger(FieldOfStudyDictionaryResource.class);

    private static final String ENTITY_NAME = "fieldOfStudyDictionary";

    private final FieldOfStudyDictionaryService fieldOfStudyDictionaryService;

    public FieldOfStudyDictionaryResource(FieldOfStudyDictionaryService fieldOfStudyDictionaryService) {
        this.fieldOfStudyDictionaryService = fieldOfStudyDictionaryService;
    }

    /**
     * POST  /field-of-study-dictionaries : Create a new fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionary the fieldOfStudyDictionary to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fieldOfStudyDictionary, or with status 400 (Bad Request) if the fieldOfStudyDictionary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/field-of-study-dictionaries")
    public ResponseEntity<FieldOfStudyDictionary> createFieldOfStudyDictionary(@RequestBody FieldOfStudyDictionary fieldOfStudyDictionary) throws URISyntaxException {
        log.debug("REST request to save FieldOfStudyDictionary : {}", fieldOfStudyDictionary);
        if (fieldOfStudyDictionary.getId() != null) {
            throw new BadRequestAlertException("A new fieldOfStudyDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldOfStudyDictionary result = fieldOfStudyDictionaryService.save(fieldOfStudyDictionary);
        return ResponseEntity.created(new URI("/api/field-of-study-dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /field-of-study-dictionaries : Updates an existing fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionary the fieldOfStudyDictionary to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fieldOfStudyDictionary,
     * or with status 400 (Bad Request) if the fieldOfStudyDictionary is not valid,
     * or with status 500 (Internal Server Error) if the fieldOfStudyDictionary couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/field-of-study-dictionaries")
    public ResponseEntity<FieldOfStudyDictionary> updateFieldOfStudyDictionary(@RequestBody FieldOfStudyDictionary fieldOfStudyDictionary) throws URISyntaxException {
        log.debug("REST request to update FieldOfStudyDictionary : {}", fieldOfStudyDictionary);
        if (fieldOfStudyDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldOfStudyDictionary result = fieldOfStudyDictionaryService.save(fieldOfStudyDictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fieldOfStudyDictionary.getId().toString()))
            .body(result);
    }

    /**
     * GET  /field-of-study-dictionaries : get all the fieldOfStudyDictionaries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fieldOfStudyDictionaries in body
     */
    @GetMapping("/field-of-study-dictionaries")
    public List<FieldOfStudyDictionary> getAllFieldOfStudyDictionaries() {
        log.debug("REST request to get all FieldOfStudyDictionaries");
        return fieldOfStudyDictionaryService.findAll();
    }

    /**
     * GET  /field-of-study-dictionaries/:id : get the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the fieldOfStudyDictionary to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fieldOfStudyDictionary, or with status 404 (Not Found)
     */
    @GetMapping("/field-of-study-dictionaries/{id}")
    public ResponseEntity<FieldOfStudyDictionary> getFieldOfStudyDictionary(@PathVariable Long id) {
        log.debug("REST request to get FieldOfStudyDictionary : {}", id);
        Optional<FieldOfStudyDictionary> fieldOfStudyDictionary = fieldOfStudyDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fieldOfStudyDictionary);
    }

    /**
     * DELETE  /field-of-study-dictionaries/:id : delete the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the fieldOfStudyDictionary to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/field-of-study-dictionaries/{id}")
    public ResponseEntity<Void> deleteFieldOfStudyDictionary(@PathVariable Long id) {
        log.debug("REST request to delete FieldOfStudyDictionary : {}", id);
        fieldOfStudyDictionaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
