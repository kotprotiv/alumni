package it.kirill.alumni.service.validation;

import it.kirill.alumni.model.ValidationResult;
import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Address;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static it.kirill.alumni.model.MessageLevel.ERROR;

@Slf4j
public class AlumniAddressFullnessValidationService implements ValidationService<AlumniDto> {

    private final String NAME = "Address Fullness Validation";

    public List<ValidationResult> validate(AlumniDto dto) {
        log.debug("AlumniAddressFullnessValidationService validating {}", dto);
        List<ValidationResult> validationResults = new ArrayList<>();
        for (Address address : dto.getAddresses()) {
            boolean isMatches = address.getStreet() != null &&
                    address.getNumber() != null &&
                    address.getCountry() != null;
            if (!isMatches) {
                String errorMessage = String.format("Address %s is not full", address);
                validationResults.add(new ValidationResult(NAME, ERROR, errorMessage));
            }
        }
        return validationResults;
    }
}
