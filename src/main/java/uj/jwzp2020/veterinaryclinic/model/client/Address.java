package uj.jwzp2020.veterinaryclinic.model.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity(name = "address")
public class Address {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @JsonProperty("street")
    @Column(length = 96)
    @Size(min = 3, max = 96)
    private final String street;

    @JsonProperty("parcelNumber")
    @Column(nullable = false)
    private final int parcelNumber;

    @JsonProperty("flatNumber")
    private final int flatNumber;

    @JsonProperty("city")
    @Column(nullable = false, length = 64)
    @Size(min = 2, max = 642)
    private final String city;

    @JsonProperty("zipcode")
    @Column(nullable = false, length = 8)
    @Size(min = 3, max = 8)
    private final String zipcode;

    @JsonProperty("country")
    @Column(nullable = false, length = 64)
    @Size(min = 4, max = 64)
    private final String country;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Address(int id, @Size(min = 3, max = 96) String street, int parcelNumber, int flatNumber, @Size(min = 2, max = 642) String city, @Size(min = 3, max = 8) String zipcode, @Size(min = 4, max = 64) String country) {
        this.id = id;
        this.street = street;
        this.parcelNumber = parcelNumber;
        this.flatNumber = flatNumber;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public int getParcelNumber() {
        return parcelNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", parcelNumber=" + parcelNumber +
                ", flatNumber=" + flatNumber +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
