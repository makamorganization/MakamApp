package makam.application.service.impl;

import makam.application.service.CourseParticipantService;
import makam.application.domain.CourseParticipant;
import makam.application.repository.CourseParticipantRepository;
import makam.application.service.dto.CourseParticipantDTO;
import makam.application.service.mapper.CourseParticipantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CourseParticipant}.
 */
@Service
@Transactional
public class CourseParticipantServiceImpl implements CourseParticipantService {

    private final Logger log = LoggerFactory.getLogger(CourseParticipantServiceImpl.class);

    private final CourseParticipantRepository courseParticipantRepository;

    private final CourseParticipantMapper courseParticipantMapper;

    public CourseParticipantServiceImpl(CourseParticipantRepository courseParticipantRepository, CourseParticipantMapper courseParticipantMapper) {
        this.courseParticipantRepository = courseParticipantRepository;
        this.courseParticipantMapper = courseParticipantMapper;
    }

    /**
     * Save a courseParticipant.
     *
     * @param courseParticipantDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CourseParticipantDTO save(CourseParticipantDTO courseParticipantDTO) {
        log.debug("Request to save CourseParticipant : {}", courseParticipantDTO);
        CourseParticipant courseParticipant = courseParticipantMapper.toEntity(courseParticipantDTO);
        courseParticipant = courseParticipantRepository.save(courseParticipant);
        return courseParticipantMapper.toDto(courseParticipant);
    }

    /**
     * Get all the courseParticipants.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseParticipantDTO> findAll() {
        log.debug("Request to get all CourseParticipants");
        return courseParticipantRepository.findAll().stream()
            .map(courseParticipantMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one courseParticipant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseParticipantDTO> findOne(Long id) {
        log.debug("Request to get CourseParticipant : {}", id);
        return courseParticipantRepository.findById(id)
            .map(courseParticipantMapper::toDto);
    }

    /**
     * Delete the courseParticipant by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseParticipant : {}", id);
        courseParticipantRepository.deleteById(id);
    }
}
