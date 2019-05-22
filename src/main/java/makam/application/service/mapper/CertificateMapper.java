package makam.application.service.mapper;

import makam.application.domain.*;
import makam.application.service.dto.CertificateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Certificate} and its DTO {@link CertificateDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserDetailsMapper.class, CourseMapper.class})
public interface CertificateMapper extends EntityMapper<CertificateDTO, Certificate> {

    @Mapping(source = "userDetails.id", target = "userDetailsId")
    @Mapping(source = "course.id", target = "courseId")
    CertificateDTO toDto(Certificate certificate);

    @Mapping(source = "userDetailsId", target = "userDetails")
    @Mapping(source = "courseId", target = "course")
    Certificate toEntity(CertificateDTO certificateDTO);

    default Certificate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Certificate certificate = new Certificate();
        certificate.setId(id);
        return certificate;
    }
}
