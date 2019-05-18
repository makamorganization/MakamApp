package makam.application.service.mapper;

import makam.application.domain.*;
import makam.application.service.dto.CourseParticipantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CourseParticipant} and its DTO {@link CourseParticipantDTO}.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, UserDetailsMapper.class})
public interface CourseParticipantMapper extends EntityMapper<CourseParticipantDTO, CourseParticipant> {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "user.id", target = "userId")
    CourseParticipantDTO toDto(CourseParticipant courseParticipant);

    @Mapping(source = "courseId", target = "course")
    @Mapping(source = "userId", target = "user")
    CourseParticipant toEntity(CourseParticipantDTO courseParticipantDTO);

    default CourseParticipant fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseParticipant courseParticipant = new CourseParticipant();
        courseParticipant.setId(id);
        return courseParticipant;
    }
}
