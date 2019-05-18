package makam.application.web.rest;

import makam.application.service.FacultyDictionaryService;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.service.dto.FacultyDictionaryDTO;

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
 * REST controller for managing {@link makam.application.domain.FacultyDictionary}.
 */
@RestController
@RequestMapping("/api")
public class FacultyDictionaryResource {

    private final Logger log = LoggerFactory.getLogger(FacultyDictionaryResource.class);

    private static final String ENTITY_NAME = "facultyDictionary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FacultyDictionaryService facultyDictionaryService;

    public FacultyDictionaryResource(FacultyDictionaryService facultyDictionaryService) {
        this.facultyDictionaryService = facultyDictionaryService;
    }

    /**
     * {@code POST  /faculty-dictionaries} : Create a new facultyDictionary.
     *
     * @param facultyDictionaryDTO the facultyDictionaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new facultyDictionaryDTO, or with status {@code 400 (Bad Request)} if the facultyDictionary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/faculty-dictionaries")
    public ResponseEntity<FacultyDictionaryDTO> createFacultyDictionary(@RequestBody FacultyDictionaryDTO facultyDictionaryDTO) throws URISyntaxException {
        log.debug("REST request to save FacultyDictionary : {}", facultyDictionaryDTO);
        if (facultyDictionaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new facultyDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FacultyDictionaryDTO result = facultyDictionaryService.save(facultyDictionaryDTO);
        return ResponseEntity.created(new URI("/api/faculty-dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /faculty-dictionaries} : Updates an existing facultyDictionary.
     *
     * @param facultyDictionaryDTO the facultyDictionaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated facultyDictionaryDTO,
     * or with status {@code 400 (Bad Request)} if the facultyDictionaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the facultyDictionaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/faculty-dictionaries")
    public ResponseEntity<FacultyDictionaryDTO> updateFacultyDictionary(@RequestBody FacultyDictionaryDTO facultyDictionaryDTO) throws URISyntaxException {
        log.debug("REST request to update FacultyDictionary : {}", facultyDictionaryDTO);
        if (facultyDictionaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FacultyDictionaryDTO result = facultyDictionaryService.save(facultyDictionaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, facultyDictionaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /faculty-dictionaries} : get all the facultyDictionaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of facultyDictionaries in body.
     */
    @GetMapping("/faculty-dictionaries")
    public List<FacultyDictionaryDTO> getAllFacultyDictionaries() {
        log.debug("REST request to get all FacultyDictionaries");
        return facultyDictionaryService.findAll();
    }

    /**
     * {@code GET  /faculty-dictionaries/:id} : get the "id" facultyDictionary.
     *
     * @param id the id of the facultyDictionaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the facultyDictionaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/faculty-dictionaries/{id}")
    public ResponseEntity<FacultyDictionaryDTO> getFacultyDictionary(@PathVariable Long id) {
        log.debug("REST request to get FacultyDictionary : {}", id);
        Optional<FacultyDictionaryDTO> facultyDictionaryDTO = facultyDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(facultyDictionaryDTO);
    }

    /**
     * {@code DELETE  /faculty-dictionaries/:id} : delete the "id" facultyDictionary.
     *
     * @param id the id of the facultyDictionaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/faculty-dictionaries/{id}")
    public ResponseEntity<Void> deleteFacultyDictionary(@PathVariable Long id) {
        log.debug("REST request to delete FacultyDictionary : {}", id);
        facultyDictionaryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
