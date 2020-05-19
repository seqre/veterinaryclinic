package uj.jwzp2020.veterinaryclinic.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;
import uj.jwzp2020.veterinaryclinic.repository.PetsRepository;

@Repository
@Profile("jpa")
public interface PetsRepositoryJpa extends JpaRepository<Pet, Long>, PetsRepository {
}
