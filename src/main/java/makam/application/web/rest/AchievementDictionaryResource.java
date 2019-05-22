package makam.application.web.rest;

import makam.application.service.AchievementDictionaryService;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.service.dto.AchievementDictionaryDTO;

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
 * REST controller for managing {@link makam.application.domain.AchievementDictionary}.
 */
@RestController
@RequestMapping("/api")
public class AchievementDictionaryResource {

    private final Logger log = LoggerFactory.getLogger(AchievementDictionaryResource.class);

    private static final String ENTITY_NAME = "achievementDictionary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AchievementDictionaryService achievementDictionaryService;

    public AchievementDictionaryResource(AchievementDictionaryService achievementDictionaryService) {
        this.achievementDictionaryService = achievementDictionaryService;
    }

    /**
     * {@code POST  /achievement-dictionaries} : Create a new achievementDictionary.
     *
     * @param achievementDictionaryDTO the achievementDictionaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new achievementDictionaryDTO, or with status {@code 400 (Bad Request)} if the achievementDictionary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/achievement-dictionaries")
    public ResponseEntity<AchievementDictionaryDTO> createAchievementDictionary(@RequestBody AchievementDictionaryDTO achievementDictionaryDTO) throws URISyntaxException {
        log.debug("REST request to save AchievementDictionary : {}", achievementDictionaryDTO);
        if (achievementDictionaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new achievementDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchievementDictionaryDTO result = achievementDictionaryService.save(achievementDictionaryDTO);
        return ResponseEntity.created(new URI("/api/achievement-dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /achievement-dictionaries} : Updates an existing achievementDictionary.
     *
     * @param achievementDictionaryDTO the achievementDictionaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated achievementDictionaryDTO,
     * or with status {@code 400 (Bad Request)} if the achievementDictionaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the achievementDictionaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/achievement-dictionaries")
    public ResponseEntity<AchievementDictionaryDTO> updateAchievementDictionary(@RequestBody AchievementDictionaryDTO achievementDictionaryDTO) throws URISyntaxException {
        log.debug("REST request to update AchievementDictionary : {}", achievementDictionaryDTO);
        if (achievementDictionaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchievementDictionaryDTO result = achievementDictionaryService.save(achievementDictionaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, achievementDictionaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /achievement-dictionaries} : get all the achievementDictionaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of achievementDictionaries in body.
     */
    @GetMapping("/achievement-dictionaries")
    public List<AchievementDictionaryDTO> getAllAchievementDictionaries() {
        log.debug("REST request to get all AchievementDictionaries");
        return achievementDictionaryService.findAll();
    }

    /**
     * {@code GET  /achievement-dictionaries/:id} : get the "id" achievementDictionary.
     *
     * @param id the id of the achievementDictionaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the achievementDictionaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/achievement-dictionaries/{id}")
    public ResponseEntity<AchievementDictionaryDTO> getAchievementDictionary(@PathVariable Long id) {
        log.debug("REST request to get AchievementDictionary : {}", id);
        Optional<AchievementDictionaryDTO> achievementDictionaryDTO = achievementDictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achievementDictionaryDTO);
    }

    /**
     * {@code DELETE  /achievement-dictionaries/:id} : delete the "id" achievementDictionary.
     *
     * @param id the id of the achievementDictionaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/achievement-dictionaries/{id}")
    public ResponseEntity<Void> deleteAchievementDictionary(@PathVariable Long id) {
        log.debug("REST request to delete AchievementDictionary : {}", id);
        achievementDictionaryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
