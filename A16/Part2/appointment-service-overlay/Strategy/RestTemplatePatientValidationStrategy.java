package com.infosys.appointmentservice.Strategy;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("restTemplateStrategy")
public class RestTemplatePatientValidationStrategy implements PatientValidationStrategy {

    private final RestTemplate restTemplate;

    public RestTemplatePatientValidationStrategy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean validatePatient(Long patientId, String token) {
        String url = "http://patient-service/api/patients/" + patientId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getStrategyName() {
        return "REST_TEMPLATE";
    }
}
