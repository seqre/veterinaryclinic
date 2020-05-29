package uj.jwzp2020.veterinaryclinic.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.model.appointment.AppointmentStatus;
import uj.jwzp2020.veterinaryclinic.model.appointment.dto.AppointmentChangeDataDTO;
import uj.jwzp2020.veterinaryclinic.repository.AppointmentRepository;
import uj.jwzp2020.veterinaryclinic.repository.PetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PetRepository petRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
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

    public Appointment changeData(Appointment appointment, AppointmentChangeDataDTO dto) {
        if (dto.getDescription() != null) appointment.setDescription(dto.getDescription());
        if (dto.getStatus() != null) appointment.setStatus(modelMapper.map(dto.getStatus(), AppointmentStatus.class));
        return appointment;
    }

    public long getCollidingCount(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findAll()
                .stream()
                .filter(app -> app.getStatus().equals(AppointmentStatus.SCHEDULED))
                .filter(app -> app.getDate().plusMinutes(app.getDuration().getMinutes()).isAfter(start))
                .filter(app -> app.getDate().isBefore(end))
                .count();
    }
}
