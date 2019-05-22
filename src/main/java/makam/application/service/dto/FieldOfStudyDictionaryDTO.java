package makam.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link makam.application.domain.FieldOfStudyDictionary} entity.
 */
public class FieldOfStudyDictionaryDTO implements Serializable {

    private Long id;

    private String key;

    private String value;


    private Long facultyDictionaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getFacultyDictionaryId() {
        return facultyDictionaryId;
    }

    public void setFacultyDictionaryId(Long facultyDictionaryId) {
        this.facultyDictionaryId = facultyDictionaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldOfStudyDictionaryDTO fieldOfStudyDictionaryDTO = (FieldOfStudyDictionaryDTO) o;
        if (fieldOfStudyDictionaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldOfStudyDictionaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldOfStudyDictionaryDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            ", facultyDictionary=" + getFacultyDictionaryId() +
            "}";
    }
}
