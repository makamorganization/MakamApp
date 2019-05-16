package makam.application.web.rest;
import makam.application.domain.UserDetailsExtras;
import makam.application.service.UserDetailsExtrasService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing UserDetailsExtras.
 */
@RestController
@RequestMapping("/api")
public class UserDetailsExtrasResource {

    private final Logger log = LoggerFactory.getLogger(UserDetailsExtrasResource.class);

    private static final String ENTITY_NAME = "userDetailsExtras";

    private final UserDetailsExtrasService userDetailsExtrasService;

    public UserDetailsExtrasResource(UserDetailsExtrasService userDetailsExtrasService) {
        this.userDetailsExtrasService = userDetailsExtrasService;
    }

    /**
     * POST  /user-details-extras : Create a new userDetailsExtras.
     *
     * @param userDetailsExtras the userDetailsExtras to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userDetailsExtras, or with status 400 (Bad Request) if the userDetailsExtras has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-details-extras")
    public ResponseEntity<UserDetailsExtras> createUserDetailsExtras(@RequestBody UserDetailsExtras userDetailsExtras) throws URISyntaxException {
        log.debug("REST request to save UserDetailsExtras : {}", userDetailsExtras);
        if (userDetailsExtras.getId() != null) {
            throw new BadRequestAlertException("A new userDetailsExtras cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserDetailsExtras result = userDetailsExtrasService.save(userDetailsExtras);
        return ResponseEntity.created(new URI("/api/user-details-extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-details-extras : Updates an existing userDetailsExtras.
     *
     * @param userDetailsExtras the userDetailsExtras to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userDetailsExtras,
     * or with status 400 (Bad Request) if the userDetailsExtras is not valid,
     * or with status 500 (Internal Server Error) if the userDetailsExtras couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-details-extras")
    public ResponseEntity<UserDetailsExtras> updateUserDetailsExtras(@RequestBody UserDetailsExtras userDetailsExtras) throws URISyntaxException {
        log.debug("REST request to update UserDetailsExtras : {}", userDetailsExtras);
        if (userDetailsExtras.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserDetailsExtras result = userDetailsExtrasService.save(userDetailsExtras);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userDetailsExtras.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-details-extras : get all the userDetailsExtras.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of userDetailsExtras in body
     */
    @GetMapping("/user-details-extras")
    public List<UserDetailsExtras> getAllUserDetailsExtras(@RequestParam(required = false) String filter) {
        if ("userdetails-is-null".equals(filter)) {
            log.debug("REST request to get all UserDetailsExtrass where userDetails is null");
            return userDetailsExtrasService.findAllWhereUserDetailsIsNull();
        }
        log.debug("REST request to get all UserDetailsExtras");
        return userDetailsExtrasService.findAll();
    }

    /**
     * GET  /user-details-extras/:id : get the "id" userDetailsExtras.
     *
     * @param id the id of the userDetailsExtras to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userDetailsExtras, or with status 404 (Not Found)
     */
    @GetMapping("/user-details-extras/{id}")
    public ResponseEntity<UserDetailsExtras> getUserDetailsExtras(@PathVariable Long id) {
        log.debug("REST request to get UserDetailsExtras : {}", id);
        Optional<UserDetailsExtras> userDetailsExtras = userDetailsExtrasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDetailsExtras);
    }

    /**
     * DELETE  /user-details-extras/:id : delete the "id" userDetailsExtras.
     *
     * @param id the id of the userDetailsExtras to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-details-extras/{id}")
    public ResponseEntity<Void> deleteUserDetailsExtras(@PathVariable Long id) {
        log.debug("REST request to delete UserDetailsExtras : {}", id);
        userDetailsExtrasService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
