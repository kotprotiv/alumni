package it.kirill.alumni.service.validation;

import it.kirill.alumni.model.ValidationResult;
import it.kirill.alumni.model.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static it.kirill.alumni.model.MessageLevel.ERROR;


@Slf4j
public class CommonValidationFacade<T> implements ValidationFacade<T> {

    private final List<ValidationService<T>> validationServices;

    public CommonValidationFacade(List<ValidationService<T>> validationServices) {
        this.validationServices = validationServices;
    }

    @Override
    public void validate(T dto) {
        log.debug("Validating {}", dto);
        List<ValidationResult> validationErrors = validationServices.stream()
                .map(s -> s.validate(dto))
                .flatMap(List::stream)
                .filter(v -> ERROR.equals(v.getLevel()))
                .collect(Collectors.toList());

        if (!validationErrors.isEmpty()) {
            throw new ValidationException(String.format("Got validation exception(s): %s", validationErrors));
        }
    }
}
