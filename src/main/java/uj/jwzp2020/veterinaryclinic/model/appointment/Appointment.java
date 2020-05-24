package uj.jwzp2020.veterinaryclinic.model.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("petId")
    @NotNull
    private Long petId;

    @JsonProperty("date")
    @NotNull
    @Future
    private LocalDateTime date;

    //TODO: implement parsing value using AppointmentLength.of()
    @JsonProperty("duration")
    @NotNull
    @Enumerated(EnumType.STRING)
    private AppointmentLength duration;

    @JsonProperty("description")
    @Column(insertable = false, length = 1536)
    @Size(max = 1536)
    private String description;
}
