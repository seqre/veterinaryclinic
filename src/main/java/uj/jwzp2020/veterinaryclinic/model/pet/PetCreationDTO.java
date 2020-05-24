package uj.jwzp2020.veterinaryclinic.model.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import uj.jwzp2020.veterinaryclinic.model.serializer.StringToLocalDateDeserializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class PetCreationDTO {

    @JsonProperty("name")
    @Size(min = 2, max = 64)
    @NotNull
    private String name;

    @JsonProperty("ownerId")
    @NotNull
    private Long ownerId;

    @JsonProperty("species")
    @NotNull
    private SpeciesDTO species;

    //TODO: Make sure it works for only year/year&month/...
    @JsonProperty("birthdate")
    @NotNull
    @PastOrPresent
    @JsonDeserialize(using = StringToLocalDateDeserializer.class)
    private LocalDate birthdate;
}
