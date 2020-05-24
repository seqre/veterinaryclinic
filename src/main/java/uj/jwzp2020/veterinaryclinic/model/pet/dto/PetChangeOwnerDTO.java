package uj.jwzp2020.veterinaryclinic.model.pet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetChangeOwnerDTO {

    @JsonProperty("ownerId")
    @NotNull
    private Long ownerId;

}
