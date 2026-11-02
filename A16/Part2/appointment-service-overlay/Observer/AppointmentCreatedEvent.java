package com.infosys.appointmentservice.Observer;

import com.infosys.appointmentservice.Entity.Appointment;
import org.springframework.context.ApplicationEvent;

public class AppointmentCreatedEvent extends ApplicationEvent {
    private final Appointment appointment;

    public AppointmentCreatedEvent(Object source, Appointment appointment) {
        super(source);
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
