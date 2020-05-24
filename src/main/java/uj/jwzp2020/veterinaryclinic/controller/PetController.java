package uj.jwzp2020.veterinaryclinic.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;
import uj.jwzp2020.veterinaryclinic.model.pet.PetCreationDTO;
import uj.jwzp2020.veterinaryclinic.model.pet.PetResponseDTO;
import uj.jwzp2020.veterinaryclinic.service.ClientService;
import uj.jwzp2020.veterinaryclinic.service.PetService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    public PetController(PetService petService, ClientService clientService, ModelMapper modelMapper) {
        this.petService = petService;
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<PetResponseDTO> getPets() {
        List<Pet> pets = petService.getPets();
        return pets.stream()
                .map(pet -> modelMapper.map(pet, PetResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PetResponseDTO getPetById(@PathVariable("id") Long id) {
        Pet pet = petService.getPetById(id);
        return modelMapper.map(pet, PetResponseDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PetResponseDTO createPet(@RequestBody PetCreationDTO dto) {
        Pet pet = modelMapper.map(dto, Pet.class);
        pet = petService.save(pet);
        return modelMapper.map(pet, PetResponseDTO.class);
    }

    @PostMapping("/multiple-add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<PetResponseDTO> createPets(@RequestBody List<PetCreationDTO> dtos) {
        return dtos.stream()
                .map(dto -> modelMapper.map(dto, Pet.class))
                .map(petService::save)
                .map(pet -> modelMapper.map(pet, PetResponseDTO.class))
                .collect(Collectors.toList());
    }
}
