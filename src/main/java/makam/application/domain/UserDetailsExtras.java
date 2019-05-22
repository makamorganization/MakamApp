package makam.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserDetailsExtras.
 */
@Entity
@Table(name = "user_details_extras")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserDetailsExtras implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "number_of_points")
    private Integer numberOfPoints;

    @Column(name = "number_of_being_late_in_row")
    private Integer numberOfBeingLateInRow;

    @Column(name = "number_of_courses_finished")
    private Integer numberOfCoursesFinished;

    @Column(name = "number_of_being_late_total")
    private Integer numberOfBeingLateTotal;

    @Column(name = "number_of_courses_total_omited")
    private Integer numberOfCoursesTotalOmited;

    @Column(name = "number_of_courses_omited_in_row")
    private Integer numberOfCoursesOmitedInRow;

    @OneToOne(mappedBy = "userDetailsExtras")
    @JsonIgnore
    private UserDetails userDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public UserDetailsExtras numberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
        return this;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Integer getNumberOfBeingLateInRow() {
        return numberOfBeingLateInRow;
    }

    public UserDetailsExtras numberOfBeingLateInRow(Integer numberOfBeingLateInRow) {
        this.numberOfBeingLateInRow = numberOfBeingLateInRow;
        return this;
    }

    public void setNumberOfBeingLateInRow(Integer numberOfBeingLateInRow) {
        this.numberOfBeingLateInRow = numberOfBeingLateInRow;
    }

    public Integer getNumberOfCoursesFinished() {
        return numberOfCoursesFinished;
    }

    public UserDetailsExtras numberOfCoursesFinished(Integer numberOfCoursesFinished) {
        this.numberOfCoursesFinished = numberOfCoursesFinished;
        return this;
    }

    public void setNumberOfCoursesFinished(Integer numberOfCoursesFinished) {
        this.numberOfCoursesFinished = numberOfCoursesFinished;
    }

    public Integer getNumberOfBeingLateTotal() {
        return numberOfBeingLateTotal;
    }

    public UserDetailsExtras numberOfBeingLateTotal(Integer numberOfBeingLateTotal) {
        this.numberOfBeingLateTotal = numberOfBeingLateTotal;
        return this;
    }

    public void setNumberOfBeingLateTotal(Integer numberOfBeingLateTotal) {
        this.numberOfBeingLateTotal = numberOfBeingLateTotal;
    }

    public Integer getNumberOfCoursesTotalOmited() {
        return numberOfCoursesTotalOmited;
    }

    public UserDetailsExtras numberOfCoursesTotalOmited(Integer numberOfCoursesTotalOmited) {
        this.numberOfCoursesTotalOmited = numberOfCoursesTotalOmited;
        return this;
    }

    public void setNumberOfCoursesTotalOmited(Integer numberOfCoursesTotalOmited) {
        this.numberOfCoursesTotalOmited = numberOfCoursesTotalOmited;
    }

    public Integer getNumberOfCoursesOmitedInRow() {
        return numberOfCoursesOmitedInRow;
    }

    public UserDetailsExtras numberOfCoursesOmitedInRow(Integer numberOfCoursesOmitedInRow) {
        this.numberOfCoursesOmitedInRow = numberOfCoursesOmitedInRow;
        return this;
    }

    public void setNumberOfCoursesOmitedInRow(Integer numberOfCoursesOmitedInRow) {
        this.numberOfCoursesOmitedInRow = numberOfCoursesOmitedInRow;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public UserDetailsExtras userDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDetailsExtras)) {
            return false;
        }
        return id != null && id.equals(((UserDetailsExtras) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserDetailsExtras{" +
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
