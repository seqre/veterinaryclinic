package uj.jwzp2020.veterinaryclinic.repository.model.appointment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uj.jwzp2020.veterinaryclinic.model.appointment.AppointmentLength;
import uj.jwzp2020.veterinaryclinic.repository.model.pet.TransportPet;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TransportAppointment {

    @JsonProperty("pet")
    @NotNull
    private final TransportPet pet;

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
    @Size(max = 1536)
    private final String description;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransportAppointment(@NotNull TransportPet pet, @NotNull @Future LocalDateTime date, @NotNull AppointmentLength duration, @Size(max = 1536) String description) {
        this.pet = pet;
        this.date = date;
        this.duration = duration;
        this.description = description;
    }

    public TransportPet getPet() {
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
        return "TransportAppointment{" +
                "pet=" + pet +
                ", date=" + date +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}
