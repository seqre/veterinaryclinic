package uj.jwzp2020.veterinaryclinic.model.appointment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @JsonProperty("pet")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private final Pet pet;

    @JsonProperty("date")
    @NotNull
    @Future
    private final LocalDateTime date;

    //TODO: implement parsing value using AppointmentLength.of()
    @JsonProperty("duration")
    @NotNull
    @Enumerated(EnumType.STRING)
    private final AppointmentLength duration;

    @JsonProperty("description")
    @Column(insertable = false, length = 1536)
    @Size(max = 1536)
    private final String description;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Appointment(Long id, @NotNull Pet pet, @NotNull @Future LocalDateTime date, @NotNull AppointmentLength duration, @Size(max = 1536) String description) {
        this.id = id;
        this.pet = pet;
        this.date = date;
        this.duration = duration;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public AppointmentLength getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", pet=" + pet +
                ", date=" + date +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}
