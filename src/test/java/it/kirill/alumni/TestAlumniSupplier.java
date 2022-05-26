package it.kirill.alumni;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Address;
import it.kirill.alumni.model.entity.Alumni;
import it.kirill.alumni.model.entity.Education;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAlumniSupplier {

    public final static String NAME = "nome";
    public final static List<Address> ADDRESSES = Collections.singletonList(
            new Address("streetname", "22", "country")
    );
    public final static Map<String, Education> EDUCATION = Collections.singletonMap("master",
            new Education("PolitecnicoMilano", 2004)
    );

    public static Alumni supplyEntity() {
        return new Alumni(NAME, ADDRESSES, EDUCATION);
    }

    public static List<Alumni> supplyEntityList() {
        return Collections.singletonList(new Alumni(NAME, ADDRESSES, EDUCATION));
    }

    public static AlumniDto supplyDto() {
        return new AlumniDto(NAME, ADDRESSES, EDUCATION);
    }

    public static List<AlumniDto> supplyDtoList() {
        return Collections.singletonList(new AlumniDto(NAME, ADDRESSES, EDUCATION));
    }

    public static Map<String, Object> supplyMap() {
        return new HashMap<>() {{
            put("totalCount", 1);
            put("data", supplyDtoList());
        }};
    }
}
