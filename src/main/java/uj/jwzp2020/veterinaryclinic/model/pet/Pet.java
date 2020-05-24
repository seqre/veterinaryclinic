package uj.jwzp2020.veterinaryclinic.model.pet;

import lombok.Data;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.model.client.Client;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client owner;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Species species;

    //TODO: Make sure it works for only year/year&month/...
    @Column(nullable = false)
    @PastOrPresent
    private LocalDate birthdate;

    @PastOrPresent
    private LocalDate deathdate;

    @Column(insertable = false)
    @OneToMany(mappedBy = "pet")
    private List<Appointment> appointments = new ArrayList<>();
}
