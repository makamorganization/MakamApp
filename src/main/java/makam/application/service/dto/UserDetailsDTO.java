package makam.application.service.dto;

import makam.application.domain.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link makam.application.domain.UserDetails} entity.
 */
public class UserDetailsDTO implements Serializable {

    private Long id;

    private String studentCardNumber;

    private String name;

    private String surname;

    private String telephoneNumber;

    private Integer studyYear;

    private String faculty;

    private String fieldOfStudy;

    private User user;

    private Long userDetailsExtrasId;

    private Set<AchievementDictionaryDTO> achievementDictionaries = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCardNumber() {
        return studentCardNumber;
    }

    public void setStudentCardNumber(String studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Long getUserDetailsExtrasId() {
        return userDetailsExtrasId;
    }

    public void setUserDetailsExtrasId(Long userDetailsExtrasId) {
        this.userDetailsExtrasId = userDetailsExtrasId;
    }

    public Set<AchievementDictionaryDTO> getAchievementDictionaries() {
        return achievementDictionaries;
    }

    public void setAchievementDictionaries(Set<AchievementDictionaryDTO> achievementDictionaries) {
        this.achievementDictionaries = achievementDictionaries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) o;
        if (userDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
            "id=" + getId() +
            ", studentCardNumber=" + getStudentCardNumber() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", telephoneNumber='" + getTelephoneNumber() + "'" +
            ", studyYear=" + getStudyYear() +
            ", faculty='" + getFaculty() + "'" +
            ", fieldOfStudy='" + getFieldOfStudy() + "'" +
            ", userDetailsExtras=" + getUserDetailsExtrasId() +
            "}";
    }
}
