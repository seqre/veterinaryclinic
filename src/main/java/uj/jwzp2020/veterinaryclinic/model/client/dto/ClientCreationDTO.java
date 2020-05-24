package uj.jwzp2020.veterinaryclinic.model.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uj.jwzp2020.veterinaryclinic.model.serializer.deserializer.StringToLocalDateDeserializer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ClientCreationDTO {

    @JsonProperty("firstName")
    @Size(min = 3, max = 64)
    @NotNull
    private String firstName;

    @JsonProperty("lastName")
    @Size(min = 2, max = 64)
    @NotNull
    private String lastName;

    @JsonProperty("birthdate")
    @NotNull
    @Past
    @JsonDeserialize(using = StringToLocalDateDeserializer.class)
    private LocalDate birthdate;

    @JsonProperty("gender")
    @NotNull
    private GenderDTO genderDTO;

    @JsonProperty("address")
    @NotNull
    private AddressDTO addressDTO;

    //TODO: Require only one contact form (allow for both)
    @JsonProperty("email")
    @Email
    private String email;

    //TODO: Add validation
    @JsonProperty("telephoneNumber")
    private String telephoneNumber;
}
