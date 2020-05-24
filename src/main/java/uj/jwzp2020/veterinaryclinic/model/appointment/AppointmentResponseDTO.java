package uj.jwzp2020.veterinaryclinic.model.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import uj.jwzp2020.veterinaryclinic.model.serializer.AppointmentLengthDTOToStringSerializer;
import uj.jwzp2020.veterinaryclinic.model.serializer.LocalDateTimeToStringSerializer;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("petId")
    @NotNull
    private Long petId;

    @JsonProperty("date")
    @NotNull
    @Future
    @JsonSerialize(using = LocalDateTimeToStringSerializer.class)
    private LocalDateTime date;

    //TODO: implement parsing value using AppointmentLength.of()
    @JsonProperty("duration")
    @NotNull
    @JsonSerialize(using = AppointmentLengthDTOToStringSerializer.class)
    private AppointmentLengthDTO duration;

    @JsonProperty("status")
    @NotNull
    private AppointmentStatusDTO status;

    @JsonProperty("description")
    @Size(max = 1536)
    private String description;
}
