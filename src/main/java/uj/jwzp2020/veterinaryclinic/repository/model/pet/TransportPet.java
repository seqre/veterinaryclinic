package uj.jwzp2020.veterinaryclinic.repository.model.pet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uj.jwzp2020.veterinaryclinic.repository.model.client.TransportClient;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TransportPet {

    @JsonProperty("name")
    @Size(min = 2, max = 64)
    @NotNull
    private final String name;

    @JsonProperty("owner")
    @NotNull
    private final TransportClient owner;

    @JsonProperty("species")
    @NotNull
    @Enumerated(EnumType.STRING)
    private final TransportSpecies species;

    //TODO: Make sure it works for only year/year&month/...
    @JsonProperty("birthdate")
    @NotNull
    @PastOrPresent
    private final LocalDate birthdate;

    @JsonProperty("deathdate")
    @PastOrPresent
    private final LocalDate deathdate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransportPet(@Size(min = 2, max = 64) @NotNull String name, @NotNull TransportClient owner, @NotNull TransportSpecies species, @NotNull @PastOrPresent LocalDate birthdate, @PastOrPresent LocalDate deathdate) {
        this.name = name;
        this.owner = owner;
        this.species = species;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
    }

    public String getName() {
        return name;
    }

    public TransportClient getOwner() {
        return owner;
    }

    public TransportSpecies getSpecies() {
        return species;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public LocalDate getDeathdate() {
        return deathdate;
    }

    @Override
    public String toString() {
        return "TransportPet{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", species=" + species +
                ", birthdate=" + birthdate +
                ", deathdate=" + deathdate +
                '}';
    }
}
