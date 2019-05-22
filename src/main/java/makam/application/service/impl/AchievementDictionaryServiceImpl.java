package makam.application.service.impl;

import makam.application.service.AchievementDictionaryService;
import makam.application.domain.AchievementDictionary;
import makam.application.repository.AchievementDictionaryRepository;
import makam.application.service.dto.AchievementDictionaryDTO;
import makam.application.service.mapper.AchievementDictionaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AchievementDictionary}.
 */
@Service
@Transactional
public class AchievementDictionaryServiceImpl implements AchievementDictionaryService {

    private final Logger log = LoggerFactory.getLogger(AchievementDictionaryServiceImpl.class);

    private final AchievementDictionaryRepository achievementDictionaryRepository;

    private final AchievementDictionaryMapper achievementDictionaryMapper;

    public AchievementDictionaryServiceImpl(AchievementDictionaryRepository achievementDictionaryRepository, AchievementDictionaryMapper achievementDictionaryMapper) {
        this.achievementDictionaryRepository = achievementDictionaryRepository;
        this.achievementDictionaryMapper = achievementDictionaryMapper;
    }

    /**
     * Save a achievementDictionary.
     *
     * @param achievementDictionaryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AchievementDictionaryDTO save(AchievementDictionaryDTO achievementDictionaryDTO) {
        log.debug("Request to save AchievementDictionary : {}", achievementDictionaryDTO);
        AchievementDictionary achievementDictionary = achievementDictionaryMapper.toEntity(achievementDictionaryDTO);
        achievementDictionary = achievementDictionaryRepository.save(achievementDictionary);
        return achievementDictionaryMapper.toDto(achievementDictionary);
    }

    /**
     * Get all the achievementDictionaries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AchievementDictionaryDTO> findAll() {
        log.debug("Request to get all AchievementDictionaries");
        return achievementDictionaryRepository.findAll().stream()
            .map(achievementDictionaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one achievementDictionary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AchievementDictionaryDTO> findOne(Long id) {
        log.debug("Request to get AchievementDictionary : {}", id);
        return achievementDictionaryRepository.findById(id)
            .map(achievementDictionaryMapper::toDto);
    }

    /**
     * Delete the achievementDictionary by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchievementDictionary : {}", id);
        achievementDictionaryRepository.deleteById(id);
    }
}
