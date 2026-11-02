package com.infosys.patientservice.Repository;

import com.infosys.patientservice.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {}
