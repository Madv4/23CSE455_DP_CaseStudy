package com.infosys.appointmentservice.Controller;

import com.infosys.appointmentservice.Entity.Appointment;
import com.infosys.appointmentservice.Repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired private AppointmentRepo appointmentRepo;
    @Autowired private RestTemplate restTemplate;
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        Object patient = restTemplate.getForObject("http://patient-service/api/patients/" + appointment.getPatientId(), Object.class);
        if (patient != null) { return appointmentRepo.save(appointment); }
        throw new RuntimeException("Invalid patient ID");
    }
    @GetMapping
    public List<Appointment> getAllAppointments() { return appointmentRepo.findAll(); }
}
