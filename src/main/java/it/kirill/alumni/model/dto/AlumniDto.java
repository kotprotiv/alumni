package it.kirill.alumni.model.dto;

import it.kirill.alumni.model.entity.Address;
import it.kirill.alumni.model.entity.Education;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AlumniDto {

    private final String name;
    private final List<Address> addresses; //todo: maybe set? if unique
    private final Map<String, Education> education;

}