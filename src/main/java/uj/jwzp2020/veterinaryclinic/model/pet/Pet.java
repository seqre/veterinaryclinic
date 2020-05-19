package uj.jwzp2020.veterinaryclinic.model.pet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uj.jwzp2020.veterinaryclinic.model.client.Client;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @JsonProperty("name")
    @Column(length = 64)
    @Size(min = 2, max = 64)
    @NotNull
    private final String name;

    @JsonProperty("owner")
    @NotNull
    @ManyToOne
    private final Client owner;

    @JsonProperty("species")
    @NotNull
    @Enumerated(EnumType.STRING)
    private final Species species;

    //TODO: Make sure it works for only year/year&month/...
    @JsonProperty("birthdate")
    @NotNull
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private final Date birthdate;

    @JsonProperty("deathdate")
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private final Date deathdate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Pet(int id, @Size(min = 2, max = 64) @NotNull String name, @NotNull Client owner, @NotNull Species species, @NotNull @PastOrPresent Date birthdate, @PastOrPresent Date deathdate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.species = species;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Client getOwner() {
        return owner;
    }

    public Species getSpecies() {
        return species;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Date getDeathdate() {
        return deathdate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", species=" + species +
                ", birthdate=" + birthdate +
                ", deathdate=" + deathdate +
                '}';
    }
}
