package makam.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "course_start_date")
    private LocalDate courseStartDate;

    @Column(name = "course_end_date")
    private LocalDate courseEndDate;

    @Column(name = "register_start_date")
    private LocalDate registerStartDate;

    @Column(name = "register_end_date")
    private LocalDate registerEndDate;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "maximum_number_of_participants")
    private Integer maximumNumberOfParticipants;

    @OneToMany(mappedBy = "course")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseParticipant> courseParticipants = new HashSet<>();
    @OneToOne(mappedBy = "course")
    @JsonIgnore
    private Certificate certificate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Course title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Course description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCourseStartDate() {
        return courseStartDate;
    }

    public Course courseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
        return this;
    }

    public void setCourseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public LocalDate getCourseEndDate() {
        return courseEndDate;
    }

    public Course courseEndDate(LocalDate courseEndDate) {
        this.courseEndDate = courseEndDate;
        return this;
    }

    public void setCourseEndDate(LocalDate courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public LocalDate getRegisterStartDate() {
        return registerStartDate;
    }

    public Course registerStartDate(LocalDate registerStartDate) {
        this.registerStartDate = registerStartDate;
        return this;
    }

    public void setRegisterStartDate(LocalDate registerStartDate) {
        this.registerStartDate = registerStartDate;
    }

    public LocalDate getRegisterEndDate() {
        return registerEndDate;
    }

    public Course registerEndDate(LocalDate registerEndDate) {
        this.registerEndDate = registerEndDate;
        return this;
    }

    public void setRegisterEndDate(LocalDate registerEndDate) {
        this.registerEndDate = registerEndDate;
    }

    public Long getDuration() {
        return duration;
    }

    public Course duration(Long duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getMaximumNumberOfParticipants() {
        return maximumNumberOfParticipants;
    }

    public Course maximumNumberOfParticipants(Integer maximumNumberOfParticipants) {
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
        return this;
    }

    public void setMaximumNumberOfParticipants(Integer maximumNumberOfParticipants) {
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
    }

    public Set<CourseParticipant> getCourseParticipants() {
        return courseParticipants;
    }

    public Course courseParticipants(Set<CourseParticipant> courseParticipants) {
        this.courseParticipants = courseParticipants;
        return this;
    }

    public Course addCourseParticipant(CourseParticipant courseParticipant) {
        this.courseParticipants.add(courseParticipant);
        courseParticipant.setCourse(this);
        return this;
    }

    public Course removeCourseParticipant(CourseParticipant courseParticipant) {
        this.courseParticipants.remove(courseParticipant);
        courseParticipant.setCourse(null);
        return this;
    }

    public void setCourseParticipants(Set<CourseParticipant> courseParticipants) {
        this.courseParticipants = courseParticipants;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public Course certificate(Certificate certificate) {
        this.certificate = certificate;
        return this;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
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
        Course course = (Course) o;
        if (course.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", courseStartDate='" + getCourseStartDate() + "'" +
            ", courseEndDate='" + getCourseEndDate() + "'" +
            ", registerStartDate='" + getRegisterStartDate() + "'" +
            ", registerEndDate='" + getRegisterEndDate() + "'" +
            ", duration=" + getDuration() +
            ", maximumNumberOfParticipants=" + getMaximumNumberOfParticipants() +
            "}";
    }
}
