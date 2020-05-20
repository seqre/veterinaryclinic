package uj.jwzp2020.veterinaryclinic.model.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity(name = "client")
@SecondaryTable(name = "address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Client {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @JsonProperty("firstName")
    @Column(length = 64)
    @Size(min = 3, max = 64)
    @NotNull
    private final String firstName;

    @JsonProperty("lastName")
    @Column(length = 64)
    @Size(min = 2, max = 64)
    @NotNull
    private final String lastName;

    @JsonProperty("birthdate")
    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private final Date birthdate;

    @JsonProperty("gender")
    @NotNull
    @Enumerated(EnumType.STRING)
    private final Gender gender;

    @JsonProperty("address")
    @NotNull
    @Embedded
    private final Address address;

    //TODO: Require only one contact form (allow for both)
    @JsonProperty("email")
    @Email
    private final String email;

    //TODO: Add validation
    @JsonProperty("telephoneNumber")
    private final String telephoneNumber;

    @JsonProperty("pets")
    @Column(insertable = false)
    @OneToMany
    private final List<Pet> pets;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Client(Long id, @Size(min = 3, max = 64) @NotNull String firstName, @Size(min = 2, max = 64) @NotNull String lastName, @NotNull @Past Date birthdate, @NotNull Gender gender, @NotNull Address address, @Email String email, String telephoneNumber, List<Pet> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public List<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", pets=" + pets +
                '}';
    }
}
