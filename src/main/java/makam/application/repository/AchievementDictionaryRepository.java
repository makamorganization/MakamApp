package makam.application.repository;

import makam.application.domain.AchievementDictionary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchievementDictionary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchievementDictionaryRepository extends JpaRepository<AchievementDictionary, Long> {

}
