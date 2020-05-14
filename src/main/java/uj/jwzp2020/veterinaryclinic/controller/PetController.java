package uj.jwzp2020.veterinaryclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import uj.jwzp2020.veterinaryclinic.repository.PetRepository;

@RestController
public class PetController {

    private final PetRepository petRepository;

    @Autowired

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
}
