package makam.application.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link makam.application.domain.Certificate} entity.
 */
public class CertificateDTO implements Serializable {

    private Long id;

    private String title;

    private String path;

    private LocalDate validityEndDate;

    @Lob
    private byte[] signature;

    private String signatureContentType;

    private Long userDetailsId;

    private Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDate getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(LocalDate validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getSignatureContentType() {
        return signatureContentType;
    }

    public void setSignatureContentType(String signatureContentType) {
        this.signatureContentType = signatureContentType;
    }

    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(Long userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CertificateDTO certificateDTO = (CertificateDTO) o;
        if (certificateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificateDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", path='" + getPath() + "'" +
            ", validityEndDate='" + getValidityEndDate() + "'" +
            ", signature='" + getSignature() + "'" +
            ", userDetails=" + getUserDetailsId() +
            ", course=" + getCourseId() +
            "}";
    }
}
