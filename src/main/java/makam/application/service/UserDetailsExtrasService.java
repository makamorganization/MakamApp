package makam.application.service;

import makam.application.domain.UserDetailsExtras;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UserDetailsExtras.
 */
public interface UserDetailsExtrasService {

    /**
     * Save a userDetailsExtras.
     *
     * @param userDetailsExtras the entity to save
     * @return the persisted entity
     */
    UserDetailsExtras save(UserDetailsExtras userDetailsExtras);

    /**
     * Get all the userDetailsExtras.
     *
     * @return the list of entities
     */
    List<UserDetailsExtras> findAll();
    /**
     * Get all the UserDetailsExtrasDTO where UserDetails is null.
     *
     * @return the list of entities
     */
    List<UserDetailsExtras> findAllWhereUserDetailsIsNull();


    /**
     * Get the "id" userDetailsExtras.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserDetailsExtras> findOne(Long id);

    /**
     * Delete the "id" userDetailsExtras.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
