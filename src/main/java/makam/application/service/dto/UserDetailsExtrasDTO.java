package makam.application.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link makam.application.domain.UserDetailsExtras} entity.
 */
public class UserDetailsExtrasDTO implements Serializable {

    private Long id;

    private Integer numberOfPoints;

    private Integer numberOfBeingLateInRow;

    private Integer numberOfCoursesFinished;

    private Integer numberOfBeingLateTotal;

    private Integer numberOfCoursesTotalOmited;

    private Integer numberOfCoursesOmitedInRow;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Integer getNumberOfBeingLateInRow() {
        return numberOfBeingLateInRow;
    }

    public void setNumberOfBeingLateInRow(Integer numberOfBeingLateInRow) {
        this.numberOfBeingLateInRow = numberOfBeingLateInRow;
    }

    public Integer getNumberOfCoursesFinished() {
        return numberOfCoursesFinished;
    }

    public void setNumberOfCoursesFinished(Integer numberOfCoursesFinished) {
        this.numberOfCoursesFinished = numberOfCoursesFinished;
    }

    public Integer getNumberOfBeingLateTotal() {
        return numberOfBeingLateTotal;
    }

    public void setNumberOfBeingLateTotal(Integer numberOfBeingLateTotal) {
        this.numberOfBeingLateTotal = numberOfBeingLateTotal;
    }

    public Integer getNumberOfCoursesTotalOmited() {
        return numberOfCoursesTotalOmited;
    }

    public void setNumberOfCoursesTotalOmited(Integer numberOfCoursesTotalOmited) {
        this.numberOfCoursesTotalOmited = numberOfCoursesTotalOmited;
    }

    public Integer getNumberOfCoursesOmitedInRow() {
        return numberOfCoursesOmitedInRow;
    }

    public void setNumberOfCoursesOmitedInRow(Integer numberOfCoursesOmitedInRow) {
        this.numberOfCoursesOmitedInRow = numberOfCoursesOmitedInRow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDetailsExtrasDTO userDetailsExtrasDTO = (UserDetailsExtrasDTO) o;
        if (userDetailsExtrasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userDetailsExtrasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserDetailsExtrasDTO{" +
            "id=" + getId() +
            ", numberOfPoints=" + getNumberOfPoints() +
            ", numberOfBeingLateInRow=" + getNumberOfBeingLateInRow() +
            ", numberOfCoursesFinished=" + getNumberOfCoursesFinished() +
            ", numberOfBeingLateTotal=" + getNumberOfBeingLateTotal() +
            ", numberOfCoursesTotalOmited=" + getNumberOfCoursesTotalOmited() +
            ", numberOfCoursesOmitedInRow=" + getNumberOfCoursesOmitedInRow() +
            "}";
    }
}
