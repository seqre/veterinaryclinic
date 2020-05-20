package uj.jwzp2020.veterinaryclinic.model.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Embeddable
@Table(name = "address")
public class Address {

    @JsonProperty("street")
    @Column(length = 96)
    @Size(min = 3, max = 96)
    private final String street;

    @JsonProperty("parcelNumber")
    @NotNull
    private final int parcelNumber;

    @JsonProperty("flatNumber")
    private final int flatNumber;

    @JsonProperty("city")
    @Column(length = 64)
    @Size(min = 2, max = 64)
    @NotNull
    private final String city;

    @JsonProperty("zipcode")
    @Column(length = 8)
    @Size(min = 3, max = 8)
    @NotNull
    private final String zipcode;

    @JsonProperty("country")
    @Column(length = 64)
    @Size(min = 4, max = 64)
    @NotNull
    private final String country;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Address(@Size(min = 3, max = 96) String street, @NotNull int parcelNumber, int flatNumber, @Size(min = 2, max = 64) @NotNull String city, @Size(min = 3, max = 8) @NotNull String zipcode, @Size(min = 4, max = 64) @NotNull String country) {
        this.street = street;
        this.parcelNumber = parcelNumber;
        this.flatNumber = flatNumber;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
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
                "street='" + street + '\'' +
                ", parcelNumber=" + parcelNumber +
                ", flatNumber=" + flatNumber +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
