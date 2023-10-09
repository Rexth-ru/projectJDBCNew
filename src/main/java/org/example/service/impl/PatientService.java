package org.example.service.impl;

import org.example.model.Patient;
import org.example.repository.PatientRepository;
import org.example.service.MyService;

import java.sql.SQLException;

public class PatientService implements MyService<Patient> {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public Patient save(Patient patient) throws SQLException {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findById(Long id) throws ClassNotFoundException {
        return patientRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return patientRepository.deleteById(id);
    }

    @Override
    public Patient update(Patient patient) throws ClassNotFoundException {
        return patientRepository.update(patient);
    }
}
