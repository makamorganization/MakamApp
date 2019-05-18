package makam.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link makam.application.domain.CourseParticipant} entity.
 */
public class CourseParticipantDTO implements Serializable {

    private Long id;

    private Boolean isUserPresent;

    private Boolean isUserLate;

    private Boolean canCancelCourseAttendance;


    private Long courseId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsUserPresent() {
        return isUserPresent;
    }

    public void setIsUserPresent(Boolean isUserPresent) {
        this.isUserPresent = isUserPresent;
    }

    public Boolean isIsUserLate() {
        return isUserLate;
    }

    public void setIsUserLate(Boolean isUserLate) {
        this.isUserLate = isUserLate;
    }

    public Boolean isCanCancelCourseAttendance() {
        return canCancelCourseAttendance;
    }

    public void setCanCancelCourseAttendance(Boolean canCancelCourseAttendance) {
        this.canCancelCourseAttendance = canCancelCourseAttendance;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userDetailsId) {
        this.userId = userDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseParticipantDTO courseParticipantDTO = (CourseParticipantDTO) o;
        if (courseParticipantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseParticipantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseParticipantDTO{" +
            "id=" + getId() +
            ", isUserPresent='" + isIsUserPresent() + "'" +
            ", isUserLate='" + isIsUserLate() + "'" +
            ", canCancelCourseAttendance='" + isCanCancelCourseAttendance() + "'" +
            ", course=" + getCourseId() +
            ", user=" + getUserId() +
            "}";
    }
}
