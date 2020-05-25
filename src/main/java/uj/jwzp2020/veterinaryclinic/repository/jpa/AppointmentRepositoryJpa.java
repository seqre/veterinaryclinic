package uj.jwzp2020.veterinaryclinic.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.repository.AppointmentRepository;

@Repository
@Profile("jpa")
public interface AppointmentRepositoryJpa extends JpaRepository<Appointment, Long>, AppointmentRepository {
}
