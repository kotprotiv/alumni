package it.kirill.alumni.service.validation;

import it.kirill.alumni.model.dto.AlumniDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class AlumniValidationFacadeImpl implements ValidationFacade<AlumniDto> {

    private final List<ValidationService<AlumniDto>> validationServices;

    public AlumniValidationFacadeImpl(List<ValidationService<AlumniDto>> validationServices) {
        this.validationServices = validationServices;
    }

    @Override
    public void validate(AlumniDto dto) {
        log.debug("Validating {}", dto);
        for (ValidationService<AlumniDto> validationService : validationServices) {
            validationService.validate(dto);
        }
    }
}
