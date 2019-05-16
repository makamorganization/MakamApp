package makam.application.service;

import makam.application.domain.AchievementDictionary;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AchievementDictionary.
 */
public interface AchievementDictionaryService {

    /**
     * Save a achievementDictionary.
     *
     * @param achievementDictionary the entity to save
     * @return the persisted entity
     */
    AchievementDictionary save(AchievementDictionary achievementDictionary);

    /**
     * Get all the achievementDictionaries.
     *
     * @return the list of entities
     */
    List<AchievementDictionary> findAll();


    /**
     * Get the "id" achievementDictionary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchievementDictionary> findOne(Long id);

    /**
     * Delete the "id" achievementDictionary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
