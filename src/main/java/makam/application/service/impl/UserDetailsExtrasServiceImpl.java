package makam.application.service.impl;

import makam.application.service.UserDetailsExtrasService;
import makam.application.domain.UserDetailsExtras;
import makam.application.repository.UserDetailsExtrasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing UserDetailsExtras.
 */
@Service
@Transactional
public class UserDetailsExtrasServiceImpl implements UserDetailsExtrasService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsExtrasServiceImpl.class);

    private final UserDetailsExtrasRepository userDetailsExtrasRepository;

    public UserDetailsExtrasServiceImpl(UserDetailsExtrasRepository userDetailsExtrasRepository) {
        this.userDetailsExtrasRepository = userDetailsExtrasRepository;
    }

    /**
     * Save a userDetailsExtras.
     *
     * @param userDetailsExtras the entity to save
     * @return the persisted entity
     */
    @Override
    public UserDetailsExtras save(UserDetailsExtras userDetailsExtras) {
        log.debug("Request to save UserDetailsExtras : {}", userDetailsExtras);
        return userDetailsExtrasRepository.save(userDetailsExtras);
    }

    /**
     * Get all the userDetailsExtras.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDetailsExtras> findAll() {
        log.debug("Request to get all UserDetailsExtras");
        return userDetailsExtrasRepository.findAll();
    }



    /**
     *  get all the userDetailsExtras where UserDetails is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<UserDetailsExtras> findAllWhereUserDetailsIsNull() {
        log.debug("Request to get all userDetailsExtras where UserDetails is null");
        return StreamSupport
            .stream(userDetailsExtrasRepository.findAll().spliterator(), false)
            .filter(userDetailsExtras -> userDetailsExtras.getUserDetails() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one userDetailsExtras by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDetailsExtras> findOne(Long id) {
        log.debug("Request to get UserDetailsExtras : {}", id);
        return userDetailsExtrasRepository.findById(id);
    }

    /**
     * Delete the userDetailsExtras by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserDetailsExtras : {}", id);
        userDetailsExtrasRepository.deleteById(id);
    }
}
