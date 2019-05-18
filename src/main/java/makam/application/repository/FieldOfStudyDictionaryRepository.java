package makam.application.repository;

import makam.application.domain.FieldOfStudyDictionary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FieldOfStudyDictionary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldOfStudyDictionaryRepository extends JpaRepository<FieldOfStudyDictionary, Long> {

}
