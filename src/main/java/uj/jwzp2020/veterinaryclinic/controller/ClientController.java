package uj.jwzp2020.veterinaryclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import uj.jwzp2020.veterinaryclinic.repository.ClientRepository;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
