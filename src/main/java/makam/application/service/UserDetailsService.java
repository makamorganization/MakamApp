package makam.application.service;

import makam.application.service.dto.UserDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link makam.application.domain.UserDetails}.
 */
public interface UserDetailsService {

    /**
     * Save a userDetails.
     *
     * @param userDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    UserDetailsDTO save(UserDetailsDTO userDetailsDTO);

    /**
     * Get all the userDetails.
     *
     * @return the list of entities.
     */
    List<UserDetailsDTO> findAll();
    /**
     * Get all the UserDetailsDTO where CourseParticipant is {@code null}.
     *
     * @return the list of entities.
     */
    List<UserDetailsDTO> findAllWhereCourseParticipantIsNull();

    /**
     * Get all the userDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<UserDetailsDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" userDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" userDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
