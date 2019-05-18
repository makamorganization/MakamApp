package makam.application.repository;

import makam.application.domain.UserDetailsExtras;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserDetailsExtras entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserDetailsExtrasRepository extends JpaRepository<UserDetailsExtras, Long> {

}
