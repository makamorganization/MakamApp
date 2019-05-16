package makam.application.service;

import makam.application.domain.CourseParticipant;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CourseParticipant.
 */
public interface CourseParticipantService {

    /**
     * Save a courseParticipant.
     *
     * @param courseParticipant the entity to save
     * @return the persisted entity
     */
    CourseParticipant save(CourseParticipant courseParticipant);

    /**
     * Get all the courseParticipants.
     *
     * @return the list of entities
     */
    List<CourseParticipant> findAll();


    /**
     * Get the "id" courseParticipant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CourseParticipant> findOne(Long id);

    /**
     * Delete the "id" courseParticipant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
