package makam.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CourseParticipant.
 */
@Entity
@Table(name = "course_participant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseParticipant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_user_present")
    private Boolean isUserPresent;

    @Column(name = "is_user_late")
    private Boolean isUserLate;

    @Column(name = "can_cancel_course_attendance")
    private Boolean canCancelCourseAttendance;

    @ManyToOne
    @JsonIgnoreProperties("courseParticipants")
    private Course course;

    @OneToOne
    @JoinColumn(unique = true)
    private UserDetails user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsUserPresent() {
        return isUserPresent;
    }

    public CourseParticipant isUserPresent(Boolean isUserPresent) {
        this.isUserPresent = isUserPresent;
        return this;
    }

    public void setIsUserPresent(Boolean isUserPresent) {
        this.isUserPresent = isUserPresent;
    }

    public Boolean isIsUserLate() {
        return isUserLate;
    }

    public CourseParticipant isUserLate(Boolean isUserLate) {
        this.isUserLate = isUserLate;
        return this;
    }

    public void setIsUserLate(Boolean isUserLate) {
        this.isUserLate = isUserLate;
    }

    public Boolean isCanCancelCourseAttendance() {
        return canCancelCourseAttendance;
    }

    public CourseParticipant canCancelCourseAttendance(Boolean canCancelCourseAttendance) {
        this.canCancelCourseAttendance = canCancelCourseAttendance;
        return this;
    }

    public void setCanCancelCourseAttendance(Boolean canCancelCourseAttendance) {
        this.canCancelCourseAttendance = canCancelCourseAttendance;
    }

    public Course getCourse() {
        return course;
    }

    public CourseParticipant course(Course course) {
        this.course = course;
        return this;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public UserDetails getUser() {
        return user;
    }

    public CourseParticipant user(UserDetails userDetails) {
        this.user = userDetails;
        return this;
    }

    public void setUser(UserDetails userDetails) {
        this.user = userDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseParticipant)) {
            return false;
        }
        return id != null && id.equals(((CourseParticipant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CourseParticipant{" +
            "id=" + getId() +
            ", isUserPresent='" + isIsUserPresent() + "'" +
            ", isUserLate='" + isIsUserLate() + "'" +
            ", canCancelCourseAttendance='" + isCanCancelCourseAttendance() + "'" +
            "}";
    }
}
