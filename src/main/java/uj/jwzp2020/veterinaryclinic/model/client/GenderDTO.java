package uj.jwzp2020.veterinaryclinic.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GenderDTO {
    @JsonProperty("male") MALE,
    @JsonProperty("female") FEMALE
}
