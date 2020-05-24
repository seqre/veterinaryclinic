package uj.jwzp2020.veterinaryclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.repository.AppointmentRepository;

import java.time.LocalDateTime;
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
        LocalDateTime start = appointment.getDate();
        LocalDateTime end = appointment.getDate().plusMinutes(appointment.getDuration().getMinutes());

        List<Appointment> appointments = appointmentRepository.findAll();
        long colliding = appointments.stream()
                .filter(app -> app.getDate().plusMinutes(app.getDuration().getMinutes()).isAfter(start))
                .filter(app -> app.getDate().isBefore(end))
                .count();

        if (colliding > 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment time cannot overlap other ones");
        }

        return appointmentRepository.save(appointment);
    }
}
