package makam.application.service.mapper;

import makam.application.domain.*;
import makam.application.service.dto.FacultyDictionaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FacultyDictionary} and its DTO {@link FacultyDictionaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FacultyDictionaryMapper extends EntityMapper<FacultyDictionaryDTO, FacultyDictionary> {


    @Mapping(target = "fieldOfStudies", ignore = true)
    FacultyDictionary toEntity(FacultyDictionaryDTO facultyDictionaryDTO);

    default FacultyDictionary fromId(Long id) {
        if (id == null) {
            return null;
        }
        FacultyDictionary facultyDictionary = new FacultyDictionary();
        facultyDictionary.setId(id);
        return facultyDictionary;
    }
}
