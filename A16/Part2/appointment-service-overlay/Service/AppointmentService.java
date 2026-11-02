package com.infosys.appointmentservice.Service;

import com.infosys.appointmentservice.Entity.Appointment;
import com.infosys.appointmentservice.Observer.AppointmentCreatedEvent;
import com.infosys.appointmentservice.Repository.AppointmentRepo;
import com.infosys.appointmentservice.Strategy.PatientValidationStrategy;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final Map<String, PatientValidationStrategy> validationStrategies;
    private final ApplicationEventPublisher eventPublisher;

    public AppointmentService(AppointmentRepo appointmentRepo,
                              Map<String, PatientValidationStrategy> validationStrategies,
                              ApplicationEventPublisher eventPublisher) {
        this.appointmentRepo = appointmentRepo;
        this.validationStrategies = validationStrategies;
        this.eventPublisher = eventPublisher;
    }

    @CircuitBreaker(name = "patientService", fallbackMethod = "fallbackValidatePatient")
    public Appointment createAppointment(Appointment appointment, String strategyType, String token) {
        PatientValidationStrategy strategy = validationStrategies.get(strategyType);
        if (strategy == null || !strategy.validatePatient(appointment.getPatientId(), token)) {
            throw new IllegalArgumentException("Patient validation failed via strategy: " + strategyType);
        }

        Appointment saved = appointmentRepo.save(appointment);
        eventPublisher.publishEvent(new AppointmentCreatedEvent(this, saved));
        return saved;
    }

    public Appointment fallbackValidatePatient(Appointment appointment, String strategyType, String token, Throwable t) {
        appointment.setStatus("PENDING_PATIENT_VERIFICATION_CIRCUIT_OPEN");
        return appointmentRepo.save(appointment);
    }
}
