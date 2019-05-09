package makam.application.repository;

import makam.application.domain.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the UserDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query(value = "select distinct user_details from UserDetails user_details left join fetch user_details.achievementDictionaries",
        countQuery = "select count(distinct user_details) from UserDetails user_details")
    Page<UserDetails> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct user_details from UserDetails user_details left join fetch user_details.achievementDictionaries")
    List<UserDetails> findAllWithEagerRelationships();

    @Query("select user_details from UserDetails user_details left join fetch user_details.achievementDictionaries where user_details.id =:id")
    Optional<UserDetails> findOneWithEagerRelationships(@Param("id") Long id);

}
