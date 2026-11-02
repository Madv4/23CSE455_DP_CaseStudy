package com.infosys.patientservice.Controller;

import com.infosys.patientservice.Entity.Patient;
import com.infosys.patientservice.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired private PatientRepo patientRepo;
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) { return patientRepo.save(patient); }
    @GetMapping
    public List<Patient> getAllPatients() { return patientRepo.findAll(); }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) { return patientRepo.findById(id).orElseThrow(() -> new RuntimeException("Patient not found")); }
}
