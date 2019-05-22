package makam.application.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link makam.application.domain.Course} entity.
 */
public class CourseDTO implements Serializable {

    private Long id;

    private String title;

    private String description;

    private LocalDate courseStartDate;

    private LocalDate courseEndDate;

    private LocalDate registerStartDate;

    private LocalDate registerEndDate;

    private Long duration;

    private Integer maximumNumberOfParticipants;

    private Integer minimalNumberOfParticipants;

    private String lecturerName;

    private String lecturerSurname;

    private Integer pointPerCourse;

    private Boolean isVisibleInApp;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public LocalDate getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(LocalDate courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public LocalDate getRegisterStartDate() {
        return registerStartDate;
    }

    public void setRegisterStartDate(LocalDate registerStartDate) {
        this.registerStartDate = registerStartDate;
    }

    public LocalDate getRegisterEndDate() {
        return registerEndDate;
    }

    public void setRegisterEndDate(LocalDate registerEndDate) {
        this.registerEndDate = registerEndDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getMaximumNumberOfParticipants() {
        return maximumNumberOfParticipants;
    }

    public void setMaximumNumberOfParticipants(Integer maximumNumberOfParticipants) {
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
    }

    public Integer getMinimalNumberOfParticipants() {
        return minimalNumberOfParticipants;
    }

    public void setMinimalNumberOfParticipants(Integer minimalNumberOfParticipants) {
        this.minimalNumberOfParticipants = minimalNumberOfParticipants;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getLecturerSurname() {
        return lecturerSurname;
    }

    public void setLecturerSurname(String lecturerSurname) {
        this.lecturerSurname = lecturerSurname;
    }

    public Integer getPointPerCourse() {
        return pointPerCourse;
    }

    public void setPointPerCourse(Integer pointPerCourse) {
        this.pointPerCourse = pointPerCourse;
    }

    public Boolean isIsVisibleInApp() {
        return isVisibleInApp;
    }

    public void setIsVisibleInApp(Boolean isVisibleInApp) {
        this.isVisibleInApp = isVisibleInApp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseDTO courseDTO = (CourseDTO) o;
        if (courseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", courseStartDate='" + getCourseStartDate() + "'" +
            ", courseEndDate='" + getCourseEndDate() + "'" +
            ", registerStartDate='" + getRegisterStartDate() + "'" +
            ", registerEndDate='" + getRegisterEndDate() + "'" +
            ", duration=" + getDuration() +
            ", maximumNumberOfParticipants=" + getMaximumNumberOfParticipants() +
            ", minimalNumberOfParticipants=" + getMinimalNumberOfParticipants() +
            ", lecturerName='" + getLecturerName() + "'" +
            ", lecturerSurname='" + getLecturerSurname() + "'" +
            ", pointPerCourse=" + getPointPerCourse() +
            ", isVisibleInApp='" + isIsVisibleInApp() + "'" +
            "}";
    }
}
