package makam.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link makam.application.domain.FacultyDictionary} entity.
 */
public class FacultyDictionaryDTO implements Serializable {

    private Long id;

    private String key;

    private String value;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacultyDictionaryDTO facultyDictionaryDTO = (FacultyDictionaryDTO) o;
        if (facultyDictionaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facultyDictionaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacultyDictionaryDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
