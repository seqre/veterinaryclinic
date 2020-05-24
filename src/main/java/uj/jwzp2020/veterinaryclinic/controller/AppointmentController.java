package uj.jwzp2020.veterinaryclinic.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uj.jwzp2020.veterinaryclinic.model.appointment.Appointment;
import uj.jwzp2020.veterinaryclinic.model.appointment.dto.AppointmentCreationDTO;
import uj.jwzp2020.veterinaryclinic.model.appointment.dto.AppointmentResponseDTO;
import uj.jwzp2020.veterinaryclinic.service.AppointmentService;
import uj.jwzp2020.veterinaryclinic.service.ClientService;
import uj.jwzp2020.veterinaryclinic.service.PetService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AppointmentResponseDTO createAppointment(@RequestBody AppointmentCreationDTO dto) {
        Appointment appointment = modelMapper.map(dto, Appointment.class);
        appointment = appointmentService.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDTO.class);
    }

    @PostMapping("/multiple-add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<AppointmentResponseDTO> createAppointments(@RequestBody List<AppointmentCreationDTO> dtos) {
        return dtos.stream()
                .map(dto -> modelMapper.map(dto, Appointment.class))
                .map(appointmentService::save)
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDTO.class))
                .collect(Collectors.toList());
    }
}
