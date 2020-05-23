package uj.jwzp2020.veterinaryclinic.repository.model.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TransportClient {

    @JsonProperty("firstName")
    @Size(min = 3, max = 64)
    @NotNull
    private final String firstName;

    @JsonProperty("lastName")
    @Size(min = 2, max = 64)
    @NotNull
    private final String lastName;

    @JsonProperty("birthdate")
    @NotNull
    @Past
    private final LocalDate birthdate;

    @JsonProperty("gender")
    @NotNull
    @Enumerated(EnumType.STRING)
    private final TransportGender gender;

    @JsonProperty("address")
    @NotNull
    private final TransportAddress address;

    //TODO: Require only one contact form (allow for both)
    @JsonProperty("email")
    @Email
    private final String email;

    //TODO: Add validation
    @JsonProperty("telephoneNumber")
    private final String telephoneNumber;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransportClient(@Size(min = 3, max = 64) @NotNull String firstName, @Size(min = 2, max = 64) @NotNull String lastName, @NotNull @Past LocalDate birthdate, @NotNull TransportGender gender, @NotNull TransportAddress address, @Email String email, String telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public TransportGender getGender() {
        return gender;
    }

    public TransportAddress getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @Override
    public String toString() {
        return "TransportClient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
