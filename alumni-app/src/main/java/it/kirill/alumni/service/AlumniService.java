package it.kirill.alumni.service;

import it.kirill.alumni.model.dto.AlumniDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface AlumniService {

    void save(AlumniDto alumniDto);

    void saveAll(List<AlumniDto> alumniDtos);

    Map<String, Object> find(String name, String educationLevel, PageRequest pageRequest);

    Map<String, Object> find(String name, String educationLevel);
}
