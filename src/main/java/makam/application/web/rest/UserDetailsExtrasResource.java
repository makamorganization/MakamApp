package makam.application.web.rest;

import makam.application.service.UserDetailsExtrasService;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.service.dto.UserDetailsExtrasDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link makam.application.domain.UserDetailsExtras}.
 */
@RestController
@RequestMapping("/api")
public class UserDetailsExtrasResource {

    private final Logger log = LoggerFactory.getLogger(UserDetailsExtrasResource.class);

    private static final String ENTITY_NAME = "userDetailsExtras";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserDetailsExtrasService userDetailsExtrasService;

    public UserDetailsExtrasResource(UserDetailsExtrasService userDetailsExtrasService) {
        this.userDetailsExtrasService = userDetailsExtrasService;
    }

    /**
     * {@code POST  /user-details-extras} : Create a new userDetailsExtras.
     *
     * @param userDetailsExtrasDTO the userDetailsExtrasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userDetailsExtrasDTO, or with status {@code 400 (Bad Request)} if the userDetailsExtras has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-details-extras")
    public ResponseEntity<UserDetailsExtrasDTO> createUserDetailsExtras(@RequestBody UserDetailsExtrasDTO userDetailsExtrasDTO) throws URISyntaxException {
        log.debug("REST request to save UserDetailsExtras : {}", userDetailsExtrasDTO);
        if (userDetailsExtrasDTO.getId() != null) {
            throw new BadRequestAlertException("A new userDetailsExtras cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserDetailsExtrasDTO result = userDetailsExtrasService.save(userDetailsExtrasDTO);
        return ResponseEntity.created(new URI("/api/user-details-extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-details-extras} : Updates an existing userDetailsExtras.
     *
     * @param userDetailsExtrasDTO the userDetailsExtrasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDetailsExtrasDTO,
     * or with status {@code 400 (Bad Request)} if the userDetailsExtrasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userDetailsExtrasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-details-extras")
    public ResponseEntity<UserDetailsExtrasDTO> updateUserDetailsExtras(@RequestBody UserDetailsExtrasDTO userDetailsExtrasDTO) throws URISyntaxException {
        log.debug("REST request to update UserDetailsExtras : {}", userDetailsExtrasDTO);
        if (userDetailsExtrasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserDetailsExtrasDTO result = userDetailsExtrasService.save(userDetailsExtrasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userDetailsExtrasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-details-extras} : get all the userDetailsExtras.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userDetailsExtras in body.
     */
    @GetMapping("/user-details-extras")
    public List<UserDetailsExtrasDTO> getAllUserDetailsExtras(@RequestParam(required = false) String filter) {
        if ("userdetails-is-null".equals(filter)) {
            log.debug("REST request to get all UserDetailsExtrass where userDetails is null");
            return userDetailsExtrasService.findAllWhereUserDetailsIsNull();
        }
        log.debug("REST request to get all UserDetailsExtras");
        return userDetailsExtrasService.findAll();
    }

    /**
     * {@code GET  /user-details-extras/:id} : get the "id" userDetailsExtras.
     *
     * @param id the id of the userDetailsExtrasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDetailsExtrasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-details-extras/{id}")
    public ResponseEntity<UserDetailsExtrasDTO> getUserDetailsExtras(@PathVariable Long id) {
        log.debug("REST request to get UserDetailsExtras : {}", id);
        Optional<UserDetailsExtrasDTO> userDetailsExtrasDTO = userDetailsExtrasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDetailsExtrasDTO);
    }

    /**
     * {@code DELETE  /user-details-extras/:id} : delete the "id" userDetailsExtras.
     *
     * @param id the id of the userDetailsExtrasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-details-extras/{id}")
    public ResponseEntity<Void> deleteUserDetailsExtras(@PathVariable Long id) {
        log.debug("REST request to delete UserDetailsExtras : {}", id);
        userDetailsExtrasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
