package makam.application.service.mapper;

import makam.application.domain.*;
import makam.application.service.dto.FieldOfStudyDictionaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FieldOfStudyDictionary} and its DTO {@link FieldOfStudyDictionaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {FacultyDictionaryMapper.class})
public interface FieldOfStudyDictionaryMapper extends EntityMapper<FieldOfStudyDictionaryDTO, FieldOfStudyDictionary> {

    @Mapping(source = "facultyDictionary.id", target = "facultyDictionaryId")
    FieldOfStudyDictionaryDTO toDto(FieldOfStudyDictionary fieldOfStudyDictionary);

    @Mapping(source = "facultyDictionaryId", target = "facultyDictionary")
    FieldOfStudyDictionary toEntity(FieldOfStudyDictionaryDTO fieldOfStudyDictionaryDTO);

    default FieldOfStudyDictionary fromId(Long id) {
        if (id == null) {
            return null;
        }
        FieldOfStudyDictionary fieldOfStudyDictionary = new FieldOfStudyDictionary();
        fieldOfStudyDictionary.setId(id);
        return fieldOfStudyDictionary;
    }
}
