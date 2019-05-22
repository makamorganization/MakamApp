package makam.application.service;

import makam.application.service.dto.UserDetailsExtrasDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link makam.application.domain.UserDetailsExtras}.
 */
public interface UserDetailsExtrasService {

    /**
     * Save a userDetailsExtras.
     *
     * @param userDetailsExtrasDTO the entity to save.
     * @return the persisted entity.
     */
    UserDetailsExtrasDTO save(UserDetailsExtrasDTO userDetailsExtrasDTO);

    /**
     * Get all the userDetailsExtras.
     *
     * @return the list of entities.
     */
    List<UserDetailsExtrasDTO> findAll();
    /**
     * Get all the UserDetailsExtrasDTO where UserDetails is {@code null}.
     *
     * @return the list of entities.
     */
    List<UserDetailsExtrasDTO> findAllWhereUserDetailsIsNull();


    /**
     * Get the "id" userDetailsExtras.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDetailsExtrasDTO> findOne(Long id);

    /**
     * Delete the "id" userDetailsExtras.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
