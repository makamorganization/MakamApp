package makam.application.web.rest;

import makam.application.service.FieldOfStudyDictionaryService;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.service.dto.FieldOfStudyDictionaryDTO;

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
 * REST controller for managing {@link makam.application.domain.FieldOfStudyDictionary}.
 */
@RestController
@RequestMapping("/api")
public class FieldOfStudyDictionaryResource {

    private final Logger log = LoggerFactory.getLogger(FieldOfStudyDictionaryResource.class);

    private static final String ENTITY_NAME = "fieldOfStudyDictionary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldOfStudyDictionaryService fieldOfStudyDictionaryService;

    public FieldOfStudyDictionaryResource(FieldOfStudyDictionaryService fieldOfStudyDictionaryService) {
        this.fieldOfStudyDictionaryService = fieldOfStudyDictionaryService;
    }

    /**
     * {@code POST  /field-of-study-dictionaries} : Create a new fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionaryDTO the fieldOfStudyDictionaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldOfStudyDictionaryDTO, or with status {@code 400 (Bad Request)} if the fieldOfStudyDictionary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-of-study-dictionaries")
    public ResponseEntity<FieldOfStudyDictionaryDTO> createFieldOfStudyDictionary(@RequestBody FieldOfStudyDictionaryDTO fieldOfStudyDictionaryDTO) throws URISyntaxException {
        log.debug("REST request to save FieldOfStudyDictionary : {}", fieldOfStudyDictionaryDTO);
        if (fieldOfStudyDictionaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new fieldOfStudyDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldOfStudyDictionaryDTO result = fieldOfStudyDictionaryService.save(fieldOfStudyDictionaryDTO);
        return ResponseEntity.created(new URI("/api/field-of-study-dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /field-of-study-dictionaries} : Updates an existing fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionaryDTO the fieldOfStudyDictionaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldOfStudyDictionaryDTO,
     * or with status {@code 400 (Bad Request)} if the fieldOfStudyDictionaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldOfStudyDictionaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-of-study-dictionaries")
    public ResponseEntity<FieldOfStudyDictionaryDTO> updateFieldOfStudyDictionary(@RequestBody FieldOfStudyDictionaryDTO fieldOfStudyDictionaryDTO) throws URISyntaxException {
        log.debug("REST request to update FieldOfStudyDictionary : {}", fieldOfStudyDictionaryDTO);
        if (fieldOfStudyDictionaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldOfStudyDictionaryDTO result = fieldOfStudyDictionaryService.save(fieldOfStudyDictionaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldOfStudyDictionaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /field-of-study-dictionaries} : get all the fieldOfStudyDictionaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldOfStudyDictionaries in body.
     */
    @GetMapping("/field-of-study-dictionaries")
    public List<FieldOfStudyDictionaryDTO> getAllFieldOfStudyDictionaries() {
        log.debug("REST request to get all FieldOfStudyDictionaries");
        return fieldOfStudyDictionaryService.findAll();
    }

    /**
     * {@code GET  /field-of-study-dictionaries/:id} : get the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the fieldOfStudyDictionaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldOfStudyDictionaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-of-study-dictionaries/{id}")
    public ResponseEntity<FieldOfStudyDictionaryDTO> getFieldOfStudyDictionary(@PathVariable Long id) {
        log.debug("REST request to get FieldOfStudyDictionary : {}", id);
        Optional<FieldOfStudyDictionaryDTO> fieldOfStudyDictionaryDTO = fieldOfStudyDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fieldOfStudyDictionaryDTO);
    }

    /**
     * {@code DELETE  /field-of-study-dictionaries/:id} : delete the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the fieldOfStudyDictionaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-of-study-dictionaries/{id}")
    public ResponseEntity<Void> deleteFieldOfStudyDictionary(@PathVariable Long id) {
        log.debug("REST request to delete FieldOfStudyDictionary : {}", id);
        fieldOfStudyDictionaryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
