package uj.jwzp2020.veterinaryclinic.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.client.Client;
import uj.jwzp2020.veterinaryclinic.repository.ClientsRepository;

@Repository
@Profile("jpa")
public interface ClientsRepositoryJpa extends JpaRepository<Client, Long>, ClientsRepository {
}
