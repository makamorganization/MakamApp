package makam.application.service.mapper;

import makam.application.domain.*;
import makam.application.service.dto.UserDetailsExtrasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserDetailsExtras} and its DTO {@link UserDetailsExtrasDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserDetailsExtrasMapper extends EntityMapper<UserDetailsExtrasDTO, UserDetailsExtras> {


    @Mapping(target = "userDetails", ignore = true)
    UserDetailsExtras toEntity(UserDetailsExtrasDTO userDetailsExtrasDTO);

    default UserDetailsExtras fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserDetailsExtras userDetailsExtras = new UserDetailsExtras();
        userDetailsExtras.setId(id);
        return userDetailsExtras;
    }
}
