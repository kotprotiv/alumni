package it.kirill.alumni.service.validation;

import it.kirill.alumni.TestAlumniSupplier;
import it.kirill.alumni.model.ValidationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static it.kirill.alumni.model.MessageLevel.ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlumniAddressStreetNameValidationServiceTest {

    private AlumniAddressStreetNameValidationService service = new AlumniAddressStreetNameValidationService();

    @Test
    void validatePositive() {
        List<ValidationResult> validationResults = service.validate(TestAlumniSupplier.supplyDto());
        assertTrue(validationResults.isEmpty());
    }

    @Test
    void validateNegative() {
        List<ValidationResult> validationResults = service.validate(TestAlumniSupplier.supplyMalformedDto());
        assertThat(validationResults)
                .hasSize(1)
                .extracting(ValidationResult::getLevel)
                .containsExactly(ERROR);
    }
}