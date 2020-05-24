package uj.jwzp2020.veterinaryclinic.repository;

import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository {

    List<Appointment> findAll();

    Appointment save(Appointment appointment);

    Optional<Appointment> findById(Long id);
}
