package it.kirill.alumni.service.validation;

import it.kirill.alumni.TestAlumniSupplier;
import it.kirill.alumni.model.ValidationResult;
import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static it.kirill.alumni.model.MessageLevel.ERROR;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommonValidationFacadeTest {

    private CommonValidationFacade<AlumniDto> validationFacade;
    private ValidationService<AlumniDto> validationService;

    @BeforeEach
    void init() {
        validationService = mock(ValidationService.class);
        when(validationService.validate(eq(TestAlumniSupplier.supplyDto())))
                .thenReturn(Collections.emptyList());
        when(validationService.validate(eq(TestAlumniSupplier.supplyMalformedDto())))
                .thenReturn(Collections.singletonList(new ValidationResult("", ERROR, "")));

        validationFacade = new CommonValidationFacade<>(Collections.singletonList(validationService));
    }

    @Test
    void validate() {
        validationFacade.validate(TestAlumniSupplier.supplyDto());
    }

    @Test
    void validateNegative() {
        assertThrows(ValidationException.class, () -> validationFacade.validate(TestAlumniSupplier.supplyMalformedDto()));
    }
}