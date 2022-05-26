package it.kirill.alumni.service.validation;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Address;
import it.kirill.alumni.model.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlumniAddressStreetNameValidationService implements ValidationService<AlumniDto> {

    public void validate(AlumniDto dto) {
        log.debug("AlumniAddressStreetNameValidationService validating {}", dto);
        for (Address address : dto.getAddresses()) {
            boolean isMatches = address.getStreet().matches("^[a-zA-Z\\s]*$");
            if (!isMatches) {
                String errorMessage = String.format("Street name %s is unacceptable", address.getStreet());
                log.error("AlumniAddressStreetNameValidationService error: {}", errorMessage);
                throw new ValidationException(errorMessage);
            }
        }
    }

    ;
}
