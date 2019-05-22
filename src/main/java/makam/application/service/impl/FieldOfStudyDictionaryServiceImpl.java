package makam.application.service.impl;

import makam.application.service.FieldOfStudyDictionaryService;
import makam.application.domain.FieldOfStudyDictionary;
import makam.application.repository.FieldOfStudyDictionaryRepository;
import makam.application.service.dto.FieldOfStudyDictionaryDTO;
import makam.application.service.mapper.FieldOfStudyDictionaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FieldOfStudyDictionary}.
 */
@Service
@Transactional
public class FieldOfStudyDictionaryServiceImpl implements FieldOfStudyDictionaryService {

    private final Logger log = LoggerFactory.getLogger(FieldOfStudyDictionaryServiceImpl.class);

    private final FieldOfStudyDictionaryRepository fieldOfStudyDictionaryRepository;

    private final FieldOfStudyDictionaryMapper fieldOfStudyDictionaryMapper;

    public FieldOfStudyDictionaryServiceImpl(FieldOfStudyDictionaryRepository fieldOfStudyDictionaryRepository, FieldOfStudyDictionaryMapper fieldOfStudyDictionaryMapper) {
        this.fieldOfStudyDictionaryRepository = fieldOfStudyDictionaryRepository;
        this.fieldOfStudyDictionaryMapper = fieldOfStudyDictionaryMapper;
    }

    /**
     * Save a fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionaryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FieldOfStudyDictionaryDTO save(FieldOfStudyDictionaryDTO fieldOfStudyDictionaryDTO) {
        log.debug("Request to save FieldOfStudyDictionary : {}", fieldOfStudyDictionaryDTO);
        FieldOfStudyDictionary fieldOfStudyDictionary = fieldOfStudyDictionaryMapper.toEntity(fieldOfStudyDictionaryDTO);
        fieldOfStudyDictionary = fieldOfStudyDictionaryRepository.save(fieldOfStudyDictionary);
        return fieldOfStudyDictionaryMapper.toDto(fieldOfStudyDictionary);
    }

    /**
     * Get all the fieldOfStudyDictionaries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FieldOfStudyDictionaryDTO> findAll() {
        log.debug("Request to get all FieldOfStudyDictionaries");
        return fieldOfStudyDictionaryRepository.findAll().stream()
            .map(fieldOfStudyDictionaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one fieldOfStudyDictionary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FieldOfStudyDictionaryDTO> findOne(Long id) {
        log.debug("Request to get FieldOfStudyDictionary : {}", id);
        return fieldOfStudyDictionaryRepository.findById(id)
            .map(fieldOfStudyDictionaryMapper::toDto);
    }

    /**
     * Delete the fieldOfStudyDictionary by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FieldOfStudyDictionary : {}", id);
        fieldOfStudyDictionaryRepository.deleteById(id);
    }
}
