package uj.jwzp2020.veterinaryclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppointmentService {

    private final RestTemplate restTemplate;

    @Autowired
    public AppointmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
