package makam.application.service.impl;

import makam.application.service.UserDetailsService;
import makam.application.domain.UserDetails;
import makam.application.repository.UserDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing UserDetails.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    /**
     * Save a userDetails.
     *
     * @param userDetails the entity to save
     * @return the persisted entity
     */
    @Override
    public UserDetails save(UserDetails userDetails) {
        log.debug("Request to save UserDetails : {}", userDetails);
        return userDetailsRepository.save(userDetails);
    }

    /**
     * Get all the userDetails.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDetails> findAll() {
        log.debug("Request to get all UserDetails");
        return userDetailsRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the UserDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<UserDetails> findAllWithEagerRelationships(Pageable pageable) {
        return userDetailsRepository.findAllWithEagerRelationships(pageable);
    }
    


    /**
     *  get all the userDetails where CourseParticipant is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<UserDetails> findAllWhereCourseParticipantIsNull() {
        log.debug("Request to get all userDetails where CourseParticipant is null");
        return StreamSupport
            .stream(userDetailsRepository.findAll().spliterator(), false)
            .filter(userDetails -> userDetails.getCourseParticipant() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one userDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDetails> findOne(Long id) {
        log.debug("Request to get UserDetails : {}", id);
        return userDetailsRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the userDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserDetails : {}", id);
        userDetailsRepository.deleteById(id);
    }
}
