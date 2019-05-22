package makam.application.service;

import makam.application.service.dto.FacultyDictionaryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link makam.application.domain.FacultyDictionary}.
 */
public interface FacultyDictionaryService {

    /**
     * Save a facultyDictionary.
     *
     * @param facultyDictionaryDTO the entity to save.
     * @return the persisted entity.
     */
    FacultyDictionaryDTO save(FacultyDictionaryDTO facultyDictionaryDTO);

    /**
     * Get all the facultyDictionaries.
     *
     * @return the list of entities.
     */
    List<FacultyDictionaryDTO> findAll();


    /**
     * Get the "id" facultyDictionary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FacultyDictionaryDTO> findOne(Long id);

    /**
     * Delete the "id" facultyDictionary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
