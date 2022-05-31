package it.kirill.alumni.model;

import lombok.Data;

@Data
public class ValidationResult {

    private final String validation;
    private final MessageLevel level;
    private final String description;
}
