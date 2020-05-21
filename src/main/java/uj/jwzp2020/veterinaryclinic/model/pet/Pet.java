package uj.jwzp2020.veterinaryclinic.model.pet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.model.client.Client;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @JsonProperty("name")
    @Column(length = 64)
    @Size(min = 2, max = 64)
    @NotNull
    private final String name;

    @JsonProperty("owner")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private final Client owner;

    @JsonProperty("species")
    @NotNull
    @Enumerated(EnumType.STRING)
    private final Species species;

    //TODO: Make sure it works for only year/year&month/...
    @JsonProperty("birthdate")
    @NotNull
    @PastOrPresent
    private final LocalDate birthdate;

    @JsonProperty("deathdate")
    @PastOrPresent
    private final LocalDate deathdate;

    @JsonProperty("appointments")
    @Column(insertable = false)
    @OneToMany(mappedBy = "pet")
    private final List<Appointment> appointments;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Pet(Long id, @Size(min = 2, max = 64) @NotNull String name, @NotNull Client owner, @NotNull Species species, @NotNull @PastOrPresent LocalDate birthdate, @PastOrPresent LocalDate deathdate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.species = species;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
        this.appointments = new LinkedList<>();
    }

    public Long getId() {
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public LocalDate getDeathdate() {
        return deathdate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
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
