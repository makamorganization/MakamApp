package makam.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AchievementDictionary.
 */
@Entity
@Table(name = "achievement_dictionary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AchievementDictionary implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_key")
    private String key;

    @Column(name = "jhi_value")
    private String value;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(mappedBy = "achievementDictionaries")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<UserDetails> userDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public AchievementDictionary key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public AchievementDictionary value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public AchievementDictionary enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserDetails> getUserDetails() {
        return userDetails;
    }

    public AchievementDictionary userDetails(Set<UserDetails> userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    public AchievementDictionary addUserDetails(UserDetails userDetails) {
        this.userDetails.add(userDetails);
        userDetails.getAchievementDictionaries().add(this);
        return this;
    }

    public AchievementDictionary removeUserDetails(UserDetails userDetails) {
        this.userDetails.remove(userDetails);
        userDetails.getAchievementDictionaries().remove(this);
        return this;
    }

    public void setUserDetails(Set<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AchievementDictionary achievementDictionary = (AchievementDictionary) o;
        if (achievementDictionary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achievementDictionary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchievementDictionary{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            ", enabled='" + isEnabled() + "'" +
            "}";
    }
}
