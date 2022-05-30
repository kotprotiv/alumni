package it.kirill.alumni.service.validation;

public interface ValidationFacade<T> {

    void validate(T dto);
}
