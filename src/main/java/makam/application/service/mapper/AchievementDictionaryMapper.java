package makam.application.service.mapper;

import makam.application.domain.*;
import makam.application.service.dto.AchievementDictionaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AchievementDictionary} and its DTO {@link AchievementDictionaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AchievementDictionaryMapper extends EntityMapper<AchievementDictionaryDTO, AchievementDictionary> {


    @Mapping(target = "userDetails", ignore = true)
    AchievementDictionary toEntity(AchievementDictionaryDTO achievementDictionaryDTO);

    default AchievementDictionary fromId(Long id) {
        if (id == null) {
            return null;
        }
        AchievementDictionary achievementDictionary = new AchievementDictionary();
        achievementDictionary.setId(id);
        return achievementDictionary;
    }
}
