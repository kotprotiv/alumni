package it.kirill.alumni.model.dto;

import it.kirill.alumni.model.entity.Address;
import it.kirill.alumni.model.entity.Education;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AlumniDto implements Serializable {

    private String name;
    private List<Address> addresses; //todo: maybe set? if unique
    private Map<String, Education> education;

    public AlumniDto() {
    }

}