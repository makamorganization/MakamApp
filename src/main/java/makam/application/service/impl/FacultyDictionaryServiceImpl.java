package makam.application.service.impl;

import makam.application.service.FacultyDictionaryService;
import makam.application.domain.FacultyDictionary;
import makam.application.repository.FacultyDictionaryRepository;
import makam.application.service.dto.FacultyDictionaryDTO;
import makam.application.service.mapper.FacultyDictionaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FacultyDictionary}.
 */
@Service
@Transactional
public class FacultyDictionaryServiceImpl implements FacultyDictionaryService {

    private final Logger log = LoggerFactory.getLogger(FacultyDictionaryServiceImpl.class);

    private final FacultyDictionaryRepository facultyDictionaryRepository;

    private final FacultyDictionaryMapper facultyDictionaryMapper;

    public FacultyDictionaryServiceImpl(FacultyDictionaryRepository facultyDictionaryRepository, FacultyDictionaryMapper facultyDictionaryMapper) {
        this.facultyDictionaryRepository = facultyDictionaryRepository;
        this.facultyDictionaryMapper = facultyDictionaryMapper;
    }

    /**
     * Save a facultyDictionary.
     *
     * @param facultyDictionaryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FacultyDictionaryDTO save(FacultyDictionaryDTO facultyDictionaryDTO) {
        log.debug("Request to save FacultyDictionary : {}", facultyDictionaryDTO);
        FacultyDictionary facultyDictionary = facultyDictionaryMapper.toEntity(facultyDictionaryDTO);
        facultyDictionary = facultyDictionaryRepository.save(facultyDictionary);
        return facultyDictionaryMapper.toDto(facultyDictionary);
    }

    /**
     * Get all the facultyDictionaries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FacultyDictionaryDTO> findAll() {
        log.debug("Request to get all FacultyDictionaries");
        return facultyDictionaryRepository.findAll().stream()
            .map(facultyDictionaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one facultyDictionary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FacultyDictionaryDTO> findOne(Long id) {
        log.debug("Request to get FacultyDictionary : {}", id);
        return facultyDictionaryRepository.findById(id)
            .map(facultyDictionaryMapper::toDto);
    }

    /**
     * Delete the facultyDictionary by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FacultyDictionary : {}", id);
        facultyDictionaryRepository.deleteById(id);
    }
}
