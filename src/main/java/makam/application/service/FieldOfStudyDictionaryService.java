package makam.application.service;

import makam.application.service.dto.FieldOfStudyDictionaryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link makam.application.domain.FieldOfStudyDictionary}.
 */
public interface FieldOfStudyDictionaryService {

    /**
     * Save a fieldOfStudyDictionary.
     *
     * @param fieldOfStudyDictionaryDTO the entity to save.
     * @return the persisted entity.
     */
    FieldOfStudyDictionaryDTO save(FieldOfStudyDictionaryDTO fieldOfStudyDictionaryDTO);

    /**
     * Get all the fieldOfStudyDictionaries.
     *
     * @return the list of entities.
     */
    List<FieldOfStudyDictionaryDTO> findAll();


    /**
     * Get the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FieldOfStudyDictionaryDTO> findOne(Long id);

    /**
     * Delete the "id" fieldOfStudyDictionary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
