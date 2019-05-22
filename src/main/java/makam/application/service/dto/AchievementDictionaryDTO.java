package makam.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link makam.application.domain.AchievementDictionary} entity.
 */
public class AchievementDictionaryDTO implements Serializable {

    private Long id;

    private String key;

    private String value;

    private Boolean enabled;

    private String description;


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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchievementDictionaryDTO achievementDictionaryDTO = (AchievementDictionaryDTO) o;
        if (achievementDictionaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achievementDictionaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchievementDictionaryDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
