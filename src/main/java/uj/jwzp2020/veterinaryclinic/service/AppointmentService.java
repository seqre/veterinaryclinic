package uj.jwzp2020.veterinaryclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.repository.AppointmentRepository;
import uj.jwzp2020.veterinaryclinic.repository.PetRepository;

import java.util.List;

@Service
public class AppointmentService {

    private final RestTemplate restTemplate;
    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;

    @Autowired
    public AppointmentService(RestTemplate restTemplate, AppointmentRepository appointmentRepository, PetRepository petRepository) {
        this.restTemplate = restTemplate;
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown appointment with id " + id));
    }

    public Appointment save(Appointment appointment) {
        petRepository.findById(appointment.getPetId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown pet with id " + appointment.getPetId()));

        return appointmentRepository.save(appointment);
    }
}
