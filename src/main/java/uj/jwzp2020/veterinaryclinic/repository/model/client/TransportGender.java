package uj.jwzp2020.veterinaryclinic.repository.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransportGender {
    @JsonProperty("male") MALE,
    @JsonProperty("female") FEMALE
}
