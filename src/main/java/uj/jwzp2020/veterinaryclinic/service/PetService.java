package uj.jwzp2020.veterinaryclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;
import uj.jwzp2020.veterinaryclinic.repository.PetRepository;

import java.util.List;

@Service
public class PetService {

    private final RestTemplate restTemplate;
    private final PetRepository petRepository;

    @Autowired
    public PetService(RestTemplate restTemplate, PetRepository petRepository) {
        this.restTemplate = restTemplate;
        this.petRepository = petRepository;
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown pet with id " + id));
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }
}
