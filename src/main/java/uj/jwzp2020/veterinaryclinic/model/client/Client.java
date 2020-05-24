package uj.jwzp2020.veterinaryclinic.model.client;

import lombok.Data;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "client")
@SecondaryTable(name = "address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String firstName;

    @Column(nullable = false, length = 64)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Embedded
    private Address address;

    private String email;

    private String telephoneNumber;

    @Column(insertable = false)
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();
}
