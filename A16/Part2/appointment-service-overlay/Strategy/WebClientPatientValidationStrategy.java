package com.infosys.appointmentservice.Strategy;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component("webClientStrategy")
public class WebClientPatientValidationStrategy implements PatientValidationStrategy {

    private final WebClient.Builder webClientBuilder;

    public WebClientPatientValidationStrategy(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public boolean validatePatient(Long patientId, String token) {
        try {
            Boolean exists = webClientBuilder.build()
                .get()
                .uri("http://patient-service/api/patients/" + patientId)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
            return Boolean.TRUE.equals(exists);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getStrategyName() {
        return "WEB_CLIENT";
    }
}
