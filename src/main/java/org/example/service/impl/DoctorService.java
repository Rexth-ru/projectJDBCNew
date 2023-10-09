package org.example.service.impl;

import org.example.model.Doctor;
import org.example.repository.DoctorRepository;
import org.example.repository.impl.DoctorRepositoryImpl;
import org.example.service.MyService;

import java.sql.SQLException;

public class DoctorService implements MyService<Doctor> {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) throws SQLException {
        return doctorRepository.save(doctor);
    }
    @Override
    public Doctor findById(Long id) {
        try {
            return doctorRepository.findById(id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (doctorRepository.deleteById(id)) return true;
        return false;
    }

    @Override
    public Doctor update(Doctor doctor) throws ClassNotFoundException {
        return doctorRepository.update(doctor);
    }

}
