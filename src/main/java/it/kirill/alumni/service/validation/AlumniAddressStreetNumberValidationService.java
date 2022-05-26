package it.kirill.alumni.service.validation;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Address;
import it.kirill.alumni.model.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlumniAddressStreetNumberValidationService implements ValidationService<AlumniDto> {

    public void validate(AlumniDto dto) {
        log.debug("AlumniAddressStreetNumberValidationService validating {}", dto);
        for (Address address : dto.getAddresses()) {
            boolean isMatches = address.getNumber().matches("^\\d*$");
            if (!isMatches) {
                String errorMessage = String.format("Street number %s is unacceptable", address.getNumber());
                log.error("AlumniAddressStreetNumberValidationService error: {}", errorMessage);
                throw new ValidationException(errorMessage);
            }
        }
    }

    ;
}
