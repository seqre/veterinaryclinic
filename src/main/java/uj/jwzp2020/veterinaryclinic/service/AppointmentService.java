package uj.jwzp2020.veterinaryclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.repository.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentService {

    private final RestTemplate restTemplate;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(RestTemplate restTemplate, AppointmentRepository appointmentRepository) {
        this.restTemplate = restTemplate;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown appointment with id " + id));
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
