package com.infosys.appointmentservice.Repository;

import com.infosys.appointmentservice.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {}
