package makam.application.web.rest;

import makam.application.service.CertificateService;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.service.dto.CertificateDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link makam.application.domain.Certificate}.
 */
@RestController
@RequestMapping("/api")
public class CertificateResource {

    private final Logger log = LoggerFactory.getLogger(CertificateResource.class);

    private static final String ENTITY_NAME = "certificate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CertificateService certificateService;

    public CertificateResource(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * {@code POST  /certificates} : Create a new certificate.
     *
     * @param certificateDTO the certificateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new certificateDTO, or with status {@code 400 (Bad Request)} if the certificate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/certificates")
    public ResponseEntity<CertificateDTO> createCertificate(@RequestBody CertificateDTO certificateDTO) throws URISyntaxException {
        log.debug("REST request to save Certificate : {}", certificateDTO);
        if (certificateDTO.getId() != null) {
            throw new BadRequestAlertException("A new certificate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificateDTO result = certificateService.save(certificateDTO);
        return ResponseEntity.created(new URI("/api/certificates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /certificates} : Updates an existing certificate.
     *
     * @param certificateDTO the certificateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificateDTO,
     * or with status {@code 400 (Bad Request)} if the certificateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the certificateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/certificates")
    public ResponseEntity<CertificateDTO> updateCertificate(@RequestBody CertificateDTO certificateDTO) throws URISyntaxException {
        log.debug("REST request to update Certificate : {}", certificateDTO);
        if (certificateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificateDTO result = certificateService.save(certificateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, certificateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /certificates} : get all the certificates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of certificates in body.
     */
    @GetMapping("/certificates")
    public List<CertificateDTO> getAllCertificates() {
        log.debug("REST request to get all Certificates");
        return certificateService.findAll();
    }

    /**
     * {@code GET  /certificates/:id} : get the "id" certificate.
     *
     * @param id the id of the certificateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the certificateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/certificates/{id}")
    public ResponseEntity<CertificateDTO> getCertificate(@PathVariable Long id) {
        log.debug("REST request to get Certificate : {}", id);
        Optional<CertificateDTO> certificateDTO = certificateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificateDTO);
    }

    /**
     * {@code DELETE  /certificates/:id} : delete the "id" certificate.
     *
     * @param id the id of the certificateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/certificates/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        log.debug("REST request to delete Certificate : {}", id);
        certificateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
