package makam.application.service;

import makam.application.domain.UserDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UserDetails.
 */
public interface UserDetailsService {

    /**
     * Save a userDetails.
     *
     * @param userDetails the entity to save
     * @return the persisted entity
     */
    UserDetails save(UserDetails userDetails);

    /**
     * Get all the userDetails.
     *
     * @return the list of entities
     */
    List<UserDetails> findAll();
    /**
     * Get all the UserDetailsDTO where CourseParticipant is null.
     *
     * @return the list of entities
     */
    List<UserDetails> findAllWhereCourseParticipantIsNull();

    /**
     * Get all the UserDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<UserDetails> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" userDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserDetails> findOne(Long id);

    /**
     * Delete the "id" userDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
