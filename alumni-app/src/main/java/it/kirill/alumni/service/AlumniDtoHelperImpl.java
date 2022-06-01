package it.kirill.alumni.service;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Alumni;

public class AlumniDtoHelperImpl implements AlumniDtoHelper {

    @Override
    public AlumniDto toDto(Alumni alumni) {
        return new AlumniDto(alumni.getName(), alumni.getAddresses(), alumni.getEducation());
    }

    @Override
    public Alumni toDomain(AlumniDto alumniDto) {
        return new Alumni(alumniDto.getName(), alumniDto.getAddresses(), alumniDto.getEducation());
    }
}
