package it.kirill.alumni.model.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    private String street;
    private String number;
    private String country;

    public Address() {
    }
}
