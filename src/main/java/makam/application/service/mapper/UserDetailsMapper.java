package makam.application.service.mapper;

import makam.application.domain.*;
import makam.application.service.dto.UserDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserDetails} and its DTO {@link UserDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserDetailsExtrasMapper.class, AchievementDictionaryMapper.class})
public interface UserDetailsMapper extends EntityMapper<UserDetailsDTO, UserDetails> {

    @Mapping(source = "userDetailsExtras.id", target = "userDetailsExtrasId")
    UserDetailsDTO toDto(UserDetails userDetails);

    @Mapping(source = "userDetailsExtrasId", target = "userDetailsExtras")
    @Mapping(target = "certificates", ignore = true)
    @Mapping(target = "courseParticipants", ignore = true)
    UserDetails toEntity(UserDetailsDTO userDetailsDTO);

    default UserDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setId(id);
        return userDetails;
    }
}
