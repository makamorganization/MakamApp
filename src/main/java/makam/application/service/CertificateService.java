package makam.application.service;

import makam.application.domain.Certificate;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Certificate.
 */
public interface CertificateService {

    /**
     * Save a certificate.
     *
     * @param certificate the entity to save
     * @return the persisted entity
     */
    Certificate save(Certificate certificate);

    /**
     * Get all the certificates.
     *
     * @return the list of entities
     */
    List<Certificate> findAll();


    /**
     * Get the "id" certificate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Certificate> findOne(Long id);

    /**
     * Delete the "id" certificate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
