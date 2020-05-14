package uj.jwzp2020.veterinaryclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import uj.jwzp2020.veterinaryclinic.repository.AppointmentRepository;
import uj.jwzp2020.veterinaryclinic.repository.ClientRepository;
import uj.jwzp2020.veterinaryclinic.repository.PetRepository;

@RestController
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final PetRepository petRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository, ClientRepository clientRepository, PetRepository petRepository) {
        this.appointmentRepository = appointmentRepository;
        this.clientRepository = clientRepository;
        this.petRepository = petRepository;
    }
}
