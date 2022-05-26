package it.kirill.alumni.service;

import it.kirill.alumni.TestAlumniSupplier;
import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.repository.AlumniRepository;
import it.kirill.alumni.service.validation.ValidationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AlumniServiceImplTest {

    private AlumniRepository alumniRepository;

    private ValidationFacade<AlumniDto> validationFacade;

    private AlumniServiceImpl alumniService;

    @BeforeEach
    void init() {
        alumniRepository = mock(AlumniRepository.class);
        when(alumniRepository.save(eq(TestAlumniSupplier.supplyEntity()))).thenReturn(TestAlumniSupplier.supplyEntity());
        when(alumniRepository.findAllByName(eq(TestAlumniSupplier.NAME), any(PageRequest.class))).thenReturn(TestAlumniSupplier.supplyEntityList());

        validationFacade = mock(ValidationFacade.class);
        doNothing().when(validationFacade).validate(eq(TestAlumniSupplier.supplyDto()));

        alumniService = new AlumniServiceImpl(alumniRepository, new AlumniDtoHelperImpl(), validationFacade);
    }

    @Test
    void save() {
        alumniService.save(TestAlumniSupplier.supplyDto());

        verify(validationFacade, times(1)).validate(eq(TestAlumniSupplier.supplyDto()));
        verify(alumniRepository, times(1)).save(eq(TestAlumniSupplier.supplyEntity()));

    }

    @Test
    void findAllByName() {
        Map<String, Object> result = alumniService.findAllByName(TestAlumniSupplier.NAME, PageRequest.of(1, 1));

        verify(alumniRepository, times(1)).findAllByName(eq(TestAlumniSupplier.NAME), eq(PageRequest.of(1, 1)));

        assertThat(result)
                .containsExactlyInAnyOrderEntriesOf(TestAlumniSupplier.supplyMap());
    }
}