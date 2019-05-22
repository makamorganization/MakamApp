package makam.application.repository;

import makam.application.domain.FacultyDictionary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FacultyDictionary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacultyDictionaryRepository extends JpaRepository<FacultyDictionary, Long> {

}
