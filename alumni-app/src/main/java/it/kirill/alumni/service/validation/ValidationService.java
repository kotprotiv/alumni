package it.kirill.alumni.service.validation;

import it.kirill.alumni.model.ValidationResult;

import java.util.List;

public interface ValidationService<T> {
    List<ValidationResult> validate(T dto);

}
