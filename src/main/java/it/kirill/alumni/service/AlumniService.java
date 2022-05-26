package it.kirill.alumni.service;

import it.kirill.alumni.model.dto.AlumniDto;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

public interface AlumniService {

    void save(AlumniDto alumniDto);

    Map<String, Object> findAllByName(String name, PageRequest pageRequest);
}
