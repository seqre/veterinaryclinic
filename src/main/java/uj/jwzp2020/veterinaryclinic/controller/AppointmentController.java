package uj.jwzp2020.veterinaryclinic.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.model.appointment.dto.AppointmentChangeDescriptionDTO;
import uj.jwzp2020.veterinaryclinic.model.appointment.dto.AppointmentCreationDTO;
import uj.jwzp2020.veterinaryclinic.model.appointment.dto.AppointmentResponseDTO;
import uj.jwzp2020.veterinaryclinic.service.AppointmentService;
import uj.jwzp2020.veterinaryclinic.service.ClientService;
import uj.jwzp2020.veterinaryclinic.service.PetService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final static LocalTime START = LocalTime.of(8, 0);
    private final static LocalTime END = LocalTime.of(20, 0);

    private final AppointmentService appointmentService;
    private final ClientService clientService;
    private final PetService petService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ClientService clientService, PetService petService, ModelMapper modelMapper) {
        this.appointmentService = appointmentService;
        this.clientService = clientService;
        this.petService = petService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<AppointmentResponseDTO> getAppointments() {
        List<Appointment> appointments = appointmentService.getAppointments();
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public AppointmentResponseDTO getAppointmentById(@PathVariable("id") Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return modelMapper.map(appointment, AppointmentResponseDTO.class);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public AppointmentResponseDTO changeAppointmentDescriptionById(@PathVariable("id") Long id, @RequestBody AppointmentChangeDescriptionDTO dto) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        appointment.setDescription(dto.getDescription());
        appointment = appointmentService.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AppointmentResponseDTO createAppointment(@RequestBody AppointmentCreationDTO dto) {
        Appointment appointment = modelMapper.map(dto, Appointment.class);

        LocalDateTime start = appointment.getDate();
        LocalDateTime end = appointment.getDate().plusMinutes(appointment.getDuration().getMinutes());

        if (!start.toLocalDate().isAfter(LocalDate.now().plusDays(1))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Visits may be arranged from the following day onwards");
        }

        if (start.toLocalTime().isBefore(START) && start.toLocalTime().plusMinutes(appointment.getDuration().getMinutes()).isAfter(END)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The visit must take place between 8:00 and 20:00");
        }

        List<Appointment> appointments = appointmentService.getAppointments();
        long colliding = appointments.stream()
                .filter(app -> app.getDate().plusMinutes(app.getDuration().getMinutes()).isAfter(start))
                .filter(app -> app.getDate().isBefore(end))
                .count();

        if (colliding > 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment time cannot overlap other ones");
        }

        appointment = appointmentService.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDTO.class);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public List<AppointmentResponseDTO> createAppointments(@RequestBody List<AppointmentCreationDTO> dtos) {
//        return dtos.stream()
//                .map(this::createAppointment)
//                .collect(Collectors.toList());
//    }
}
