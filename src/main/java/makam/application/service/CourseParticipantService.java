package makam.application.service;

import makam.application.service.dto.CourseParticipantDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link makam.application.domain.CourseParticipant}.
 */
public interface CourseParticipantService {

    /**
     * Save a courseParticipant.
     *
     * @param courseParticipantDTO the entity to save.
     * @return the persisted entity.
     */
    CourseParticipantDTO save(CourseParticipantDTO courseParticipantDTO);

    /**
     * Get all the courseParticipants.
     *
     * @return the list of entities.
     */
    List<CourseParticipantDTO> findAll();


    /**
     * Get the "id" courseParticipant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CourseParticipantDTO> findOne(Long id);

    /**
     * Delete the "id" courseParticipant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
