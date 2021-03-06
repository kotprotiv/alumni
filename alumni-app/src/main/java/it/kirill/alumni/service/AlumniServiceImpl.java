package it.kirill.alumni.service;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Alumni;
import it.kirill.alumni.model.exception.NoDataFoundException;
import it.kirill.alumni.repository.AlumniRepository;
import it.kirill.alumni.service.validation.ValidationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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
        log.debug("Saving {}", alumniDto);
        alumniDtoValidationFacade.validate(alumniDto);
        Alumni alumni = alumniDtoHelper.toDomain(alumniDto);
        alumniRepository.save(alumni);
    }

    @Override
    public void saveAll(List<AlumniDto> alumniDtos) {
        log.debug("Saving {}", alumniDtos);
        List<Alumni> alumni = alumniDtos.stream().peek(alumniDtoValidationFacade::validate).map(alumniDtoHelper::toDomain).collect(Collectors.toList());
        alumniRepository.saveAll(alumni);
    }

    @Override
    @Cacheable(value = "alumniCachePaged")
    public Map<String, Object> find(String name, String educationLevel, PageRequest pageRequest) {
        log.debug("Finding {} with {} and {}", name, educationLevel, pageRequest);
        List<Alumni> alumni = alumniRepository.findAllByName(name, pageRequest);
        return getAlumniDtoMap(educationLevel, alumni);
    }

    @Override
    @Cacheable(value = "alumniCache")
    public Map<String, Object> find(String name, String educationLevel) {
        log.debug("Finding {} with {}", name, educationLevel);
        List<Alumni> alumni = alumniRepository.findAllByName(name);
        return getAlumniDtoMap(educationLevel, alumni);
    }

    private Map<String, Object> getAlumniDtoMap(String educationLevel, List<Alumni> alumni) {
        List<AlumniDto> alumniDtos = alumni.stream()
                .map(alumniDtoHelper::toDto)
                .filter(d -> d.getEducation().containsKey(educationLevel))
                .collect(Collectors.toList());

        if (alumniDtos.isEmpty()) {
            throw new NoDataFoundException("No alumni found");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", alumniDtos.size());
        result.put("data", alumniDtos);
        return result;
    }
}
