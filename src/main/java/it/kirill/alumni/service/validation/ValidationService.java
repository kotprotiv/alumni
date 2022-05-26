package it.kirill.alumni.service.validation;

public interface ValidationService<T> {
    void validate(T dto);
}
