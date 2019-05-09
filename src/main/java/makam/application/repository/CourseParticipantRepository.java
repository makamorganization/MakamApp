package makam.application.repository;

import makam.application.domain.CourseParticipant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CourseParticipant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseParticipantRepository extends JpaRepository<CourseParticipant, Long> {

}
