package makam.application.repository;

import makam.application.domain.Course;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Course entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c join CourseParticipant cp on c.id = cp.course.id " +
        "join UserDetails ud on ud.id = cp.user.id join User u on u.id = ud.user.id " +
        "where u.id = :userId")
    List<Course> findAllByUserId(@Param("userId") Long userId);

    @Query("select c from Course c join CourseParticipant cp on c.id = cp.course.id " +
        "join UserDetails ud on ud.id = cp.user.id join User u on u.id = ud.user.id " +
        "where u.id = :userId and c.id = :courseId")
    List<Course> findAllByUserIdAndCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);
}
