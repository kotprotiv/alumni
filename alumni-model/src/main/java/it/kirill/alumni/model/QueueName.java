package it.kirill.alumni.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QueueName {

    REQUEST("requestQueue");

    @Getter
    private final String name;
}
