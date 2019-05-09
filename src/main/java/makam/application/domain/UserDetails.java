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
 * A UserDetails.
 */
@Entity
@Table(name = "user_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "student_card_number")
    private Integer studentCardNumber;

    @OneToMany(mappedBy = "userDetails")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Certificate> certificates = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "user_details_achievement_dictionary",
               joinColumns = @JoinColumn(name = "user_details_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "achievement_dictionary_id", referencedColumnName = "id"))
    private Set<AchievementDictionary> achievementDictionaries = new HashSet<>();

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private CourseParticipant courseParticipant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudentCardNumber() {
        return studentCardNumber;
    }

    public UserDetails studentCardNumber(Integer studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
        return this;
    }

    public void setStudentCardNumber(Integer studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public UserDetails certificates(Set<Certificate> certificates) {
        this.certificates = certificates;
        return this;
    }

    public UserDetails addCertificate(Certificate certificate) {
        this.certificates.add(certificate);
        certificate.setUserDetails(this);
        return this;
    }

    public UserDetails removeCertificate(Certificate certificate) {
        this.certificates.remove(certificate);
        certificate.setUserDetails(null);
        return this;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    public Set<AchievementDictionary> getAchievementDictionaries() {
        return achievementDictionaries;
    }

    public UserDetails achievementDictionaries(Set<AchievementDictionary> achievementDictionaries) {
        this.achievementDictionaries = achievementDictionaries;
        return this;
    }

    public UserDetails addAchievementDictionary(AchievementDictionary achievementDictionary) {
        this.achievementDictionaries.add(achievementDictionary);
        achievementDictionary.getUserDetails().add(this);
        return this;
    }

    public UserDetails removeAchievementDictionary(AchievementDictionary achievementDictionary) {
        this.achievementDictionaries.remove(achievementDictionary);
        achievementDictionary.getUserDetails().remove(this);
        return this;
    }

    public void setAchievementDictionaries(Set<AchievementDictionary> achievementDictionaries) {
        this.achievementDictionaries = achievementDictionaries;
    }

    public CourseParticipant getCourseParticipant() {
        return courseParticipant;
    }

    public UserDetails courseParticipant(CourseParticipant courseParticipant) {
        this.courseParticipant = courseParticipant;
        return this;
    }

    public void setCourseParticipant(CourseParticipant courseParticipant) {
        this.courseParticipant = courseParticipant;
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
        UserDetails userDetails = (UserDetails) o;
        if (userDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserDetails{" +
            "id=" + getId() +
            ", studentCardNumber=" + getStudentCardNumber() +
            "}";
    }
}
