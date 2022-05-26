package it.kirill.alumni.service.validation;

import it.kirill.alumni.TestAlumniSupplier;
import org.junit.jupiter.api.Test;

class AlumniAddressStreetNameValidationServiceTest {

    private AlumniAddressStreetNameValidationService service = new AlumniAddressStreetNameValidationService();

    @Test
    void validatePositive() {
        service.validate(TestAlumniSupplier.supplyDto());
    }
}