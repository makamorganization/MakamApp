package makam.application.web.rest;
import makam.application.domain.AchievementDictionary;
import makam.application.repository.AchievementDictionaryRepository;
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
 * REST controller for managing AchievementDictionary.
 */
@RestController
@RequestMapping("/api")
public class AchievementDictionaryResource {

    private final Logger log = LoggerFactory.getLogger(AchievementDictionaryResource.class);

    private static final String ENTITY_NAME = "achievementDictionary";

    private final AchievementDictionaryRepository achievementDictionaryRepository;

    public AchievementDictionaryResource(AchievementDictionaryRepository achievementDictionaryRepository) {
        this.achievementDictionaryRepository = achievementDictionaryRepository;
    }

    /**
     * POST  /achievement-dictionaries : Create a new achievementDictionary.
     *
     * @param achievementDictionary the achievementDictionary to create
     * @return the ResponseEntity with status 201 (Created) and with body the new achievementDictionary, or with status 400 (Bad Request) if the achievementDictionary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/achievement-dictionaries")
    public ResponseEntity<AchievementDictionary> createAchievementDictionary(@RequestBody AchievementDictionary achievementDictionary) throws URISyntaxException {
        log.debug("REST request to save AchievementDictionary : {}", achievementDictionary);
        if (achievementDictionary.getId() != null) {
            throw new BadRequestAlertException("A new achievementDictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchievementDictionary result = achievementDictionaryRepository.save(achievementDictionary);
        return ResponseEntity.created(new URI("/api/achievement-dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /achievement-dictionaries : Updates an existing achievementDictionary.
     *
     * @param achievementDictionary the achievementDictionary to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated achievementDictionary,
     * or with status 400 (Bad Request) if the achievementDictionary is not valid,
     * or with status 500 (Internal Server Error) if the achievementDictionary couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/achievement-dictionaries")
    public ResponseEntity<AchievementDictionary> updateAchievementDictionary(@RequestBody AchievementDictionary achievementDictionary) throws URISyntaxException {
        log.debug("REST request to update AchievementDictionary : {}", achievementDictionary);
        if (achievementDictionary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchievementDictionary result = achievementDictionaryRepository.save(achievementDictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, achievementDictionary.getId().toString()))
            .body(result);
    }

    /**
     * GET  /achievement-dictionaries : get all the achievementDictionaries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of achievementDictionaries in body
     */
    @GetMapping("/achievement-dictionaries")
    public List<AchievementDictionary> getAllAchievementDictionaries() {
        log.debug("REST request to get all AchievementDictionaries");
        return achievementDictionaryRepository.findAll();
    }

    /**
     * GET  /achievement-dictionaries/:id : get the "id" achievementDictionary.
     *
     * @param id the id of the achievementDictionary to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the achievementDictionary, or with status 404 (Not Found)
     */
    @GetMapping("/achievement-dictionaries/{id}")
    public ResponseEntity<AchievementDictionary> getAchievementDictionary(@PathVariable Long id) {
        log.debug("REST request to get AchievementDictionary : {}", id);
        Optional<AchievementDictionary> achievementDictionary = achievementDictionaryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(achievementDictionary);
    }

    /**
     * DELETE  /achievement-dictionaries/:id : delete the "id" achievementDictionary.
     *
     * @param id the id of the achievementDictionary to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/achievement-dictionaries/{id}")
    public ResponseEntity<Void> deleteAchievementDictionary(@PathVariable Long id) {
        log.debug("REST request to delete AchievementDictionary : {}", id);
        achievementDictionaryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
