package org.example.repository;

import org.example.model.Patient;
import org.example.repository.GeneralRepository;

import java.util.UUID;

public interface PatientRepository extends GeneralRepository<Patient, Long> {
}
