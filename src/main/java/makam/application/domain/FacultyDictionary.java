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
 * A FacultyDictionary.
 */
@Entity
@Table(name = "faculty_dictionary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FacultyDictionary implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_key")
    private String key;

    @Column(name = "jhi_value")
    private String value;

    @OneToMany(mappedBy = "facultyDictionary")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FieldOfStudyDictionary> fieldOfStudies = new HashSet<>();
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

    public FacultyDictionary key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public FacultyDictionary value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<FieldOfStudyDictionary> getFieldOfStudies() {
        return fieldOfStudies;
    }

    public FacultyDictionary fieldOfStudies(Set<FieldOfStudyDictionary> fieldOfStudyDictionaries) {
        this.fieldOfStudies = fieldOfStudyDictionaries;
        return this;
    }

    public FacultyDictionary addFieldOfStudy(FieldOfStudyDictionary fieldOfStudyDictionary) {
        this.fieldOfStudies.add(fieldOfStudyDictionary);
        fieldOfStudyDictionary.setFacultyDictionary(this);
        return this;
    }

    public FacultyDictionary removeFieldOfStudy(FieldOfStudyDictionary fieldOfStudyDictionary) {
        this.fieldOfStudies.remove(fieldOfStudyDictionary);
        fieldOfStudyDictionary.setFacultyDictionary(null);
        return this;
    }

    public void setFieldOfStudies(Set<FieldOfStudyDictionary> fieldOfStudyDictionaries) {
        this.fieldOfStudies = fieldOfStudyDictionaries;
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
        FacultyDictionary facultyDictionary = (FacultyDictionary) o;
        if (facultyDictionary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facultyDictionary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacultyDictionary{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
