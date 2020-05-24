package uj.jwzp2020.veterinaryclinic.model.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import uj.jwzp2020.veterinaryclinic.model.serializer.StringToAppointmentLengthDTODeserializer;
import uj.jwzp2020.veterinaryclinic.model.serializer.StringToLocalDateTimeDeserializer;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentCreationDTO {

    @JsonProperty("petId")
    @NotNull
    private Long petId;

    @JsonProperty("date")
    @NotNull
    @Future
    @JsonDeserialize(using = StringToLocalDateTimeDeserializer.class)
    private LocalDateTime date;

    //TODO: implement parsing value using AppointmentLength.of()
    @JsonProperty("duration")
    @NotNull
    @JsonDeserialize(using = StringToAppointmentLengthDTODeserializer.class)
    private AppointmentLengthDTO duration;

    @JsonProperty("status")
    @NotNull
    private AppointmentStatusDTO status;
}
