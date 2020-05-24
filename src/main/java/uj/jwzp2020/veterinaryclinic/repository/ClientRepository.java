package uj.jwzp2020.veterinaryclinic.repository;

import org.springframework.stereotype.Repository;
import uj.jwzp2020.veterinaryclinic.model.client.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository {

    List<Client> findAll();

    Client save(Client client);

    Optional<Client> findById(Long id);
}
