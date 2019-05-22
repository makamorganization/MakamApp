package makam.application.service.impl;

import makam.application.service.UserDetailsService;
import makam.application.domain.UserDetails;
import makam.application.repository.UserDetailsRepository;
import makam.application.service.dto.UserDetailsDTO;
import makam.application.service.mapper.UserDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link UserDetails}.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserDetailsRepository userDetailsRepository;

    private final UserDetailsMapper userDetailsMapper;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository, UserDetailsMapper userDetailsMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    /**
     * Save a userDetails.
     *
     * @param userDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserDetailsDTO save(UserDetailsDTO userDetailsDTO) {
        log.debug("Request to save UserDetails : {}", userDetailsDTO);
        UserDetails userDetails = userDetailsMapper.toEntity(userDetailsDTO);
        userDetails = userDetailsRepository.save(userDetails);
        return userDetailsMapper.toDto(userDetails);
    }

    /**
     * Get all the userDetails.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDetailsDTO> findAll() {
        log.debug("Request to get all UserDetails");
        return userDetailsRepository.findAllWithEagerRelationships().stream()
            .map(userDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the userDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<UserDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return userDetailsRepository.findAllWithEagerRelationships(pageable).map(userDetailsMapper::toDto);
    }
    


    /**
    *  Get all the userDetails where CourseParticipant is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<UserDetailsDTO> findAllWhereCourseParticipantIsNull() {
        log.debug("Request to get all userDetails where CourseParticipant is null");
        return StreamSupport
            .stream(userDetailsRepository.findAll().spliterator(), false)
            .filter(userDetails -> userDetails.getCourseParticipant() == null)
            .map(userDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDetailsDTO> findOne(Long id) {
        log.debug("Request to get UserDetails : {}", id);
        return userDetailsRepository.findOneWithEagerRelationships(id)
            .map(userDetailsMapper::toDto);
    }

    /**
     * Delete the userDetails by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserDetails : {}", id);
        userDetailsRepository.deleteById(id);
    }
}
