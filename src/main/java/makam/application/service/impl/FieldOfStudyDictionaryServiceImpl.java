package makam.application.service.impl;

import makam.application.service.FieldOfStudyDictionaryService;
import makam.application.domain.FieldOfStudyDictionary;
import makam.application.repository.FieldOfStudyDictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing FieldOfStudyDictionary.
 */
@Service
@Transactional
public class FieldOfStudyDictionaryServiceImpl implements FieldOfStudyDictionaryService {

    private final Logger log = LoggerFactory.getLogger(FieldOfStudyDictionaryServiceImpl.class);

    private final FieldOfStudyDictionaryRepository fieldOfStudyDictionaryRepository;

    public FieldOfStudyDictionaryServiceImpl(FieldOfStudyDictionaryRepository fieldOfStudyDictionaryRepository) {
        this.fieldOfStudyDictionaryRepository = fieldOfStudyDictionaryRepository;
    }

    /**
     * Save a fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionary the entity to save
     * @return the persisted entity
     */
    @Override
    public FieldOfStudyDictionary save(FieldOfStudyDictionary fieldOfStudyDictionary) {
        log.debug("Request to save FieldOfStudyDictionary : {}", fieldOfStudyDictionary);
        return fieldOfStudyDictionaryRepository.save(fieldOfStudyDictionary);
    }

    /**
     * Get all the fieldOfStudyDictionaries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FieldOfStudyDictionary> findAll() {
        log.debug("Request to get all FieldOfStudyDictionaries");
        return fieldOfStudyDictionaryRepository.findAll();
    }


    /**
     * Get one fieldOfStudyDictionary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FieldOfStudyDictionary> findOne(Long id) {
        log.debug("Request to get FieldOfStudyDictionary : {}", id);
        return fieldOfStudyDictionaryRepository.findById(id);
    }

    /**
     * Delete the fieldOfStudyDictionary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FieldOfStudyDictionary : {}", id);
        fieldOfStudyDictionaryRepository.deleteById(id);
    }
}
