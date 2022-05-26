package it.kirill.alumni.service.validation;

import it.kirill.alumni.TestAlumniSupplier;
import org.junit.jupiter.api.Test;

class AlumniAddressFullnessValidationServiceTest {

    private AlumniAddressFullnessValidationService service = new AlumniAddressFullnessValidationService();

    @Test
    void validatePositive() {
        service.validate(TestAlumniSupplier.supplyDto());
    }
}