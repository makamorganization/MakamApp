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

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "study_year")
    private Integer studyYear;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "field_of_study")
    private String fieldOfStudy;

    @OneToOne
    @JoinColumn(unique = true)
    private UserDetailsExtras userDetailsExtras;

    @OneToOne
    @JoinColumn(name="JHI_USER_ID", unique = true)
    private User user;

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

    public String getName() {
        return name;
    }

    public UserDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public UserDetails surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public UserDetails telephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public UserDetails studyYear(Integer studyYear) {
        this.studyYear = studyYear;
        return this;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

    public String getFaculty() {
        return faculty;
    }

    public UserDetails faculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public UserDetails fieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
        return this;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public UserDetailsExtras getUserDetailsExtras() {
        return userDetailsExtras;
    }

    public UserDetails userDetailsExtras(UserDetailsExtras userDetailsExtras) {
        this.userDetailsExtras = userDetailsExtras;
        return this;
    }

    public void setUserDetailsExtras(UserDetailsExtras userDetailsExtras) {
        this.userDetailsExtras = userDetailsExtras;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDetails)) {
            return false;
        }
        return id != null && id.equals(((UserDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
            "id=" + getId() +
            ", studentCardNumber=" + getStudentCardNumber() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", telephoneNumber='" + getTelephoneNumber() + "'" +
            ", studyYear=" + getStudyYear() +
            ", faculty='" + getFaculty() + "'" +
            ", fieldOfStudy='" + getFieldOfStudy() + "'" +
            "}";
    }
}
