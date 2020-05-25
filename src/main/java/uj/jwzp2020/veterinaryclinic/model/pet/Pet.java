package uj.jwzp2020.veterinaryclinic.model.pet;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Entity(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Species species;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate birthdate;

    @PastOrPresent
    private LocalDate deathdate;
}
