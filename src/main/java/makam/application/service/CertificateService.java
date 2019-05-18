package makam.application.service;

import makam.application.service.dto.CertificateDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link makam.application.domain.Certificate}.
 */
public interface CertificateService {

    /**
     * Save a certificate.
     *
     * @param certificateDTO the entity to save.
     * @return the persisted entity.
     */
    CertificateDTO save(CertificateDTO certificateDTO);

    /**
     * Get all the certificates.
     *
     * @return the list of entities.
     */
    List<CertificateDTO> findAll();


    /**
     * Get the "id" certificate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CertificateDTO> findOne(Long id);

    /**
     * Delete the "id" certificate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
