package com.infosys.appointmentservice.Strategy;

public interface PatientValidationStrategy {
    boolean validatePatient(Long patientId, String token);
    String getStrategyName();
}
