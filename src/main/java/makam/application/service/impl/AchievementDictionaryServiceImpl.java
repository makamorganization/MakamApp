package makam.application.service.impl;

import makam.application.service.AchievementDictionaryService;
import makam.application.domain.AchievementDictionary;
import makam.application.repository.AchievementDictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing AchievementDictionary.
 */
@Service
@Transactional
public class AchievementDictionaryServiceImpl implements AchievementDictionaryService {

    private final Logger log = LoggerFactory.getLogger(AchievementDictionaryServiceImpl.class);

    private final AchievementDictionaryRepository achievementDictionaryRepository;

    public AchievementDictionaryServiceImpl(AchievementDictionaryRepository achievementDictionaryRepository) {
        this.achievementDictionaryRepository = achievementDictionaryRepository;
    }

    /**
     * Save a achievementDictionary.
     *
     * @param achievementDictionary the entity to save
     * @return the persisted entity
     */
    @Override
    public AchievementDictionary save(AchievementDictionary achievementDictionary) {
        log.debug("Request to save AchievementDictionary : {}", achievementDictionary);
        return achievementDictionaryRepository.save(achievementDictionary);
    }

    /**
     * Get all the achievementDictionaries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AchievementDictionary> findAll() {
        log.debug("Request to get all AchievementDictionaries");
        return achievementDictionaryRepository.findAll();
    }


    /**
     * Get one achievementDictionary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchievementDictionary> findOne(Long id) {
        log.debug("Request to get AchievementDictionary : {}", id);
        return achievementDictionaryRepository.findById(id);
    }

    /**
     * Delete the achievementDictionary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchievementDictionary : {}", id);
        achievementDictionaryRepository.deleteById(id);
    }
}
