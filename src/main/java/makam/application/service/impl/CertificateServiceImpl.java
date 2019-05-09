package makam.application.service.impl;

import makam.application.service.CertificateService;
import makam.application.domain.Certificate;
import makam.application.repository.CertificateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Certificate.
 */
@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {

    private final Logger log = LoggerFactory.getLogger(CertificateServiceImpl.class);

    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    /**
     * Save a certificate.
     *
     * @param certificate the entity to save
     * @return the persisted entity
     */
    @Override
    public Certificate save(Certificate certificate) {
        log.debug("Request to save Certificate : {}", certificate);
        return certificateRepository.save(certificate);
    }

    /**
     * Get all the certificates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Certificate> findAll() {
        log.debug("Request to get all Certificates");
        return certificateRepository.findAll();
    }


    /**
     * Get one certificate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Certificate> findOne(Long id) {
        log.debug("Request to get Certificate : {}", id);
        return certificateRepository.findById(id);
    }

    /**
     * Delete the certificate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Certificate : {}", id);
        certificateRepository.deleteById(id);
    }
}
