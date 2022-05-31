package it.kirill.alumni.service;

import it.kirill.alumni.TestAlumniSupplier;
import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Alumni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlumniDtoHelperImplTest {

    private AlumniDtoHelperImpl alumniDtoHelper;

    @BeforeEach
    void init() {
        alumniDtoHelper = new AlumniDtoHelperImpl();
    }

    @Test
    void toDto() {
        AlumniDto alumniDto = alumniDtoHelper.toDto(TestAlumniSupplier.supplyEntity());
        assertEquals(TestAlumniSupplier.supplyDto(), alumniDto);
    }

    @Test
    void toDomain() {
        Alumni alumni = alumniDtoHelper.toDomain(TestAlumniSupplier.supplyDto());
        assertEquals(TestAlumniSupplier.supplyEntity(), alumni);
    }
}