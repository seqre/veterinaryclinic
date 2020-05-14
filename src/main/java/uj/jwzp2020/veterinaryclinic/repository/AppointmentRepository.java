package uj.jwzp2020.veterinaryclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
