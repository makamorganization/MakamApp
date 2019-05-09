package makam.application.web.rest;
import makam.application.domain.Certificate;
import makam.application.service.CertificateService;
import makam.application.web.rest.errors.BadRequestAlertException;
import makam.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Certificate.
 */
@RestController
@RequestMapping("/api")
public class CertificateResource {

    private final Logger log = LoggerFactory.getLogger(CertificateResource.class);

    private static final String ENTITY_NAME = "certificate";

    private final CertificateService certificateService;

    public CertificateResource(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * POST  /certificates : Create a new certificate.
     *
     * @param certificate the certificate to create
     * @return the ResponseEntity with status 201 (Created) and with body the new certificate, or with status 400 (Bad Request) if the certificate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/certificates")
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate certificate) throws URISyntaxException {
        log.debug("REST request to save Certificate : {}", certificate);
        if (certificate.getId() != null) {
            throw new BadRequestAlertException("A new certificate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Certificate result = certificateService.save(certificate);
        return ResponseEntity.created(new URI("/api/certificates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /certificates : Updates an existing certificate.
     *
     * @param certificate the certificate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated certificate,
     * or with status 400 (Bad Request) if the certificate is not valid,
     * or with status 500 (Internal Server Error) if the certificate couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/certificates")
    public ResponseEntity<Certificate> updateCertificate(@RequestBody Certificate certificate) throws URISyntaxException {
        log.debug("REST request to update Certificate : {}", certificate);
        if (certificate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Certificate result = certificateService.save(certificate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, certificate.getId().toString()))
            .body(result);
    }

    /**
     * GET  /certificates : get all the certificates.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of certificates in body
     */
    @GetMapping("/certificates")
    public List<Certificate> getAllCertificates() {
        log.debug("REST request to get all Certificates");
        return certificateService.findAll();
    }

    /**
     * GET  /certificates/:id : get the "id" certificate.
     *
     * @param id the id of the certificate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the certificate, or with status 404 (Not Found)
     */
    @GetMapping("/certificates/{id}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long id) {
        log.debug("REST request to get Certificate : {}", id);
        Optional<Certificate> certificate = certificateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificate);
    }

    /**
     * DELETE  /certificates/:id : delete the "id" certificate.
     *
     * @param id the id of the certificate to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/certificates/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        log.debug("REST request to delete Certificate : {}", id);
        certificateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
