package uj.jwzp2020.veterinaryclinic.repository;

import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository {

    List<Pet> findAll();

    Pet save(Pet pet);

    Optional<Pet> findById(Long id);
}
