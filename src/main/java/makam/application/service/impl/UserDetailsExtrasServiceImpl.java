package makam.application.service.impl;

import makam.application.service.UserDetailsExtrasService;
import makam.application.domain.UserDetailsExtras;
import makam.application.repository.UserDetailsExtrasRepository;
import makam.application.service.dto.UserDetailsExtrasDTO;
import makam.application.service.mapper.UserDetailsExtrasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link UserDetailsExtras}.
 */
@Service
@Transactional
public class UserDetailsExtrasServiceImpl implements UserDetailsExtrasService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsExtrasServiceImpl.class);

    private final UserDetailsExtrasRepository userDetailsExtrasRepository;

    private final UserDetailsExtrasMapper userDetailsExtrasMapper;

    public UserDetailsExtrasServiceImpl(UserDetailsExtrasRepository userDetailsExtrasRepository, UserDetailsExtrasMapper userDetailsExtrasMapper) {
        this.userDetailsExtrasRepository = userDetailsExtrasRepository;
        this.userDetailsExtrasMapper = userDetailsExtrasMapper;
    }

    /**
     * Save a userDetailsExtras.
     *
     * @param userDetailsExtrasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserDetailsExtrasDTO save(UserDetailsExtrasDTO userDetailsExtrasDTO) {
        log.debug("Request to save UserDetailsExtras : {}", userDetailsExtrasDTO);
        UserDetailsExtras userDetailsExtras = userDetailsExtrasMapper.toEntity(userDetailsExtrasDTO);
        userDetailsExtras = userDetailsExtrasRepository.save(userDetailsExtras);
        return userDetailsExtrasMapper.toDto(userDetailsExtras);
    }

    /**
     * Get all the userDetailsExtras.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDetailsExtrasDTO> findAll() {
        log.debug("Request to get all UserDetailsExtras");
        return userDetailsExtrasRepository.findAll().stream()
            .map(userDetailsExtrasMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the userDetailsExtras where UserDetails is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<UserDetailsExtrasDTO> findAllWhereUserDetailsIsNull() {
        log.debug("Request to get all userDetailsExtras where UserDetails is null");
        return StreamSupport
            .stream(userDetailsExtrasRepository.findAll().spliterator(), false)
            .filter(userDetailsExtras -> userDetailsExtras.getUserDetails() == null)
            .map(userDetailsExtrasMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userDetailsExtras by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDetailsExtrasDTO> findOne(Long id) {
        log.debug("Request to get UserDetailsExtras : {}", id);
        return userDetailsExtrasRepository.findById(id)
            .map(userDetailsExtrasMapper::toDto);
    }

    /**
     * Delete the userDetailsExtras by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserDetailsExtras : {}", id);
        userDetailsExtrasRepository.deleteById(id);
    }
}
