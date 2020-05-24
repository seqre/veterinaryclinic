package uj.jwzp2020.veterinaryclinic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import uj.jwzp2020.veterinaryclinic.model.client.Client;
import uj.jwzp2020.veterinaryclinic.model.client.ClientCreationDTO;
import uj.jwzp2020.veterinaryclinic.model.client.ClientResponseDTO;

@SpringBootApplication
public class VeterinaryClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinaryClinicApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        TypeMap<ClientCreationDTO, Client> creationToClient = mapper.createTypeMap(ClientCreationDTO.class, Client.class);
        creationToClient.addMappings(mapping -> {
            mapping.map(ClientCreationDTO::getAddressDTO, Client::setAddress);
            mapping.map(ClientCreationDTO::getGenderDTO, Client::setGender);
        });

        TypeMap<Client, ClientResponseDTO> clientToResponse = mapper.createTypeMap(Client.class, ClientResponseDTO.class);
        clientToResponse.addMappings(mapping -> {
            mapping.map(Client::getGender, ClientResponseDTO::setGenderDTO);
            mapping.map(Client::getAddress, ClientResponseDTO::setAddressDTO);
        });

        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
