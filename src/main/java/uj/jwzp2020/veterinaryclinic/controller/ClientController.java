package uj.jwzp2020.veterinaryclinic.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uj.jwzp2020.veterinaryclinic.model.client.Client;
import uj.jwzp2020.veterinaryclinic.model.client.ClientCreationDTO;
import uj.jwzp2020.veterinaryclinic.model.client.ClientResponseDTO;
import uj.jwzp2020.veterinaryclinic.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ModelMapper modelMapper;
    private final ClientService clientService;

    @Autowired
    public ClientController(ModelMapper modelMapper, ClientService clientService) {
        this.modelMapper = modelMapper;
        this.clientService = clientService;
    }

    @GetMapping
    @ResponseBody
    public List<ClientResponseDTO> getClients() {
        List<Client> clients = clientService.getClients();
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ClientResponseDTO getClientById(@PathVariable("id") Long id) {
        Client client = clientService.getClientById(id);
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ClientResponseDTO createClient(@RequestBody ClientCreationDTO dto) {
        Client client = modelMapper.map(dto, Client.class);
        client = clientService.save(client);
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    @PostMapping("/multiple-add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<ClientResponseDTO> createClients(@RequestBody List<ClientCreationDTO> dtos) {
        return dtos.stream()
                .map(dto -> modelMapper.map(dto, Client.class))
                .map(clientService::save)
                .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                .collect(Collectors.toList());
    }
}
