package makam.application.service;

import makam.application.service.dto.AchievementDictionaryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link makam.application.domain.AchievementDictionary}.
 */
public interface AchievementDictionaryService {

    /**
     * Save a achievementDictionary.
     *
     * @param achievementDictionaryDTO the entity to save.
     * @return the persisted entity.
     */
    AchievementDictionaryDTO save(AchievementDictionaryDTO achievementDictionaryDTO);

    /**
     * Get all the achievementDictionaries.
     *
     * @return the list of entities.
     */
    List<AchievementDictionaryDTO> findAll();


    /**
     * Get the "id" achievementDictionary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AchievementDictionaryDTO> findOne(Long id);

    /**
     * Delete the "id" achievementDictionary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
