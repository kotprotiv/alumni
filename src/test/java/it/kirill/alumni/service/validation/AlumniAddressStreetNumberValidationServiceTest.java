package it.kirill.alumni.service.validation;

import it.kirill.alumni.TestAlumniSupplier;
import org.junit.jupiter.api.Test;

class AlumniAddressStreetNumberValidationServiceTest {

    private AlumniAddressStreetNumberValidationService service = new AlumniAddressStreetNumberValidationService();

    @Test
    void validatePositive() {
        service.validate(TestAlumniSupplier.supplyDto());
    }
}