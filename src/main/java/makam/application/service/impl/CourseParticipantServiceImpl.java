package makam.application.service.impl;

import makam.application.service.CourseParticipantService;
import makam.application.domain.CourseParticipant;
import makam.application.repository.CourseParticipantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing CourseParticipant.
 */
@Service
@Transactional
public class CourseParticipantServiceImpl implements CourseParticipantService {

    private final Logger log = LoggerFactory.getLogger(CourseParticipantServiceImpl.class);

    private final CourseParticipantRepository courseParticipantRepository;

    public CourseParticipantServiceImpl(CourseParticipantRepository courseParticipantRepository) {
        this.courseParticipantRepository = courseParticipantRepository;
    }

    /**
     * Save a courseParticipant.
     *
     * @param courseParticipant the entity to save
     * @return the persisted entity
     */
    @Override
    public CourseParticipant save(CourseParticipant courseParticipant) {
        log.debug("Request to save CourseParticipant : {}", courseParticipant);
        return courseParticipantRepository.save(courseParticipant);
    }

    /**
     * Get all the courseParticipants.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseParticipant> findAll() {
        log.debug("Request to get all CourseParticipants");
        return courseParticipantRepository.findAll();
    }


    /**
     * Get one courseParticipant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseParticipant> findOne(Long id) {
        log.debug("Request to get CourseParticipant : {}", id);
        return courseParticipantRepository.findById(id);
    }

    /**
     * Delete the courseParticipant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseParticipant : {}", id);
        courseParticipantRepository.deleteById(id);
    }
}
