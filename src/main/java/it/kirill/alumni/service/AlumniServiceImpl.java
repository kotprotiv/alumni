package it.kirill.alumni.service;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Alumni;
import it.kirill.alumni.model.exception.NoDataFoundException;
import it.kirill.alumni.repository.AlumniRepository;
import it.kirill.alumni.service.validation.ValidationFacade;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlumniServiceImpl implements AlumniService {

    private final AlumniRepository alumniRepository;

    private final AlumniDtoHelper alumniDtoHelper;

    private final ValidationFacade<AlumniDto> alumniDtoValidationFacade;

    public AlumniServiceImpl(AlumniRepository alumniRepository, AlumniDtoHelper alumniDtoHelper,
                             ValidationFacade<AlumniDto> alumniDtoValidationFacade) {
        this.alumniRepository = alumniRepository;
        this.alumniDtoHelper = alumniDtoHelper;
        this.alumniDtoValidationFacade = alumniDtoValidationFacade;
    }

    @Override
    public void save(AlumniDto alumniDto) {
        alumniDtoValidationFacade.validate(alumniDto);
        Alumni alumni = alumniDtoHelper.toDomain(alumniDto);
        alumniRepository.save(alumni);
    }

    @Override
    public Map<String, Object> findAllByName(String name, PageRequest pageRequest) {
        List<AlumniDto> alumniDtos = alumniRepository.findAllByName(name, pageRequest).stream()
                .map(alumniDtoHelper::toDto)
                .collect(Collectors.toList());

        if (alumniDtos.isEmpty()) {
            throw new NoDataFoundException(String.format("No alumni found by name %s", name));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", alumniDtos.size());
        result.put("data", alumniDtos);
        return result;
    }
}
