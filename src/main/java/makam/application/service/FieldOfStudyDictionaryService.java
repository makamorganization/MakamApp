package makam.application.service;

import makam.application.domain.FieldOfStudyDictionary;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FieldOfStudyDictionary.
 */
public interface FieldOfStudyDictionaryService {

    /**
     * Save a fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionary the entity to save
     * @return the persisted entity
     */
    FieldOfStudyDictionary save(FieldOfStudyDictionary fieldOfStudyDictionary);

    /**
     * Get all the fieldOfStudyDictionaries.
     *
     * @return the list of entities
     */
    List<FieldOfStudyDictionary> findAll();


    /**
     * Get the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FieldOfStudyDictionary> findOne(Long id);

    /**
     * Delete the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
