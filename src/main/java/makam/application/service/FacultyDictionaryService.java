package makam.application.service;

import makam.application.domain.FacultyDictionary;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FacultyDictionary.
 */
public interface FacultyDictionaryService {

    /**
     * Save a facultyDictionary.
     *
     * @param facultyDictionary the entity to save
     * @return the persisted entity
     */
    FacultyDictionary save(FacultyDictionary facultyDictionary);

    /**
     * Get all the facultyDictionaries.
     *
     * @return the list of entities
     */
    List<FacultyDictionary> findAll();


    /**
     * Get the "id" facultyDictionary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FacultyDictionary> findOne(Long id);

    /**
     * Delete the "id" facultyDictionary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
