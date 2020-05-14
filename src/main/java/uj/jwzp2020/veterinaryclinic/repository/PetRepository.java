package uj.jwzp2020.veterinaryclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}