package makam.application.service.impl;

import makam.application.service.FacultyDictionaryService;
import makam.application.domain.FacultyDictionary;
import makam.application.repository.FacultyDictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing FacultyDictionary.
 */
@Service
@Transactional
public class FacultyDictionaryServiceImpl implements FacultyDictionaryService {

    private final Logger log = LoggerFactory.getLogger(FacultyDictionaryServiceImpl.class);

    private final FacultyDictionaryRepository facultyDictionaryRepository;

    public FacultyDictionaryServiceImpl(FacultyDictionaryRepository facultyDictionaryRepository) {
        this.facultyDictionaryRepository = facultyDictionaryRepository;
    }

    /**
     * Save a facultyDictionary.
     *
     * @param facultyDictionary the entity to save
     * @return the persisted entity
     */
    @Override
    public FacultyDictionary save(FacultyDictionary facultyDictionary) {
        log.debug("Request to save FacultyDictionary : {}", facultyDictionary);
        return facultyDictionaryRepository.save(facultyDictionary);
    }

    /**
     * Get all the facultyDictionaries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FacultyDictionary> findAll() {
        log.debug("Request to get all FacultyDictionaries");
        return facultyDictionaryRepository.findAll();
    }


    /**
     * Get one facultyDictionary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FacultyDictionary> findOne(Long id) {
        log.debug("Request to get FacultyDictionary : {}", id);
        return facultyDictionaryRepository.findById(id);
    }

    /**
     * Delete the facultyDictionary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FacultyDictionary : {}", id);
        facultyDictionaryRepository.deleteById(id);
    }
}
