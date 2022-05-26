package it.kirill.alumni.service.validation;

import it.kirill.alumni.model.dto.AlumniDto;
import it.kirill.alumni.model.entity.Address;
import it.kirill.alumni.model.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlumniAddressFullnessValidationService implements ValidationService<AlumniDto> {

    public void validate(AlumniDto dto) {
        log.debug("AlumniAddressFullnessValidationService validating {}", dto);
        for (Address address : dto.getAddresses()) {
            boolean isMatches = address.getStreet() != null &&
                    address.getNumber() != null &&
                    address.getCountry() != null;
            if (!isMatches) {
                String errorMessage = String.format("Address %s is not full", address);
                log.error("AlumniAddressFullnessValidationService error: {}", errorMessage);
                throw new ValidationException(errorMessage);
            }
        }
    }

    ;
}
