package uj.jwzp2020.veterinaryclinic.repository.model.pet;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransportSpecies {
    @JsonProperty("other") OTHER,
    @JsonProperty("dog") DOG,
    @JsonProperty("cat") CAT,
    @JsonProperty("bird") BIRD,
    @JsonProperty("rodent") RODENT,
    @JsonProperty("amphibian") AMPHIBIAN
}
