package com.infosys.appointmentservice.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AppointmentAuditObserver {

    private static final Logger log = LoggerFactory.getLogger(AppointmentAuditObserver.class);

    @EventListener
    @Async
    public void onAppointmentCreated(AppointmentCreatedEvent event) {
        log.info("[OBSERVER AUDIT] Appointment booked successfully: ID={}, PatientID={}, Doctor={}",
            event.getAppointment().getId(),
            event.getAppointment().getPatientId(),
            event.getAppointment().getDoctorName());
    }
}
