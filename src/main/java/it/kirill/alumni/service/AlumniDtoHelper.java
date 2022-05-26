package it.kirill.alumni.service;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Alumni;

public interface AlumniDtoHelper {

    AlumniDto toDto(Alumni alumni);

    Alumni toDomain(AlumniDto alumniDto); //todo: to entity?
}
