package it.kirill.alumni.model.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Alumni {

    private final String name;
    private final List<Address> addresses;
    private final Map<String, Education> education;
}
