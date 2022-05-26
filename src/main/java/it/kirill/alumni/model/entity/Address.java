package it.kirill.alumni.model.entity;

import lombok.Data;

@Data
public class Address {

    private final String street;
    private final String number;
    private final String country;
}
