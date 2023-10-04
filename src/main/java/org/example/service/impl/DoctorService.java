package org.example.service.impl;

import org.example.model.Doctor;
import org.example.repository.DoctorRepository;
import org.example.service.MyService;

import java.sql.SQLException;
import java.util.List;

public class DoctorService implements MyService<Doctor> {
    private DoctorRepository doctorRepository;


    @Override
    public Doctor save(Doctor doctor) throws SQLException {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        if (doctorRepository.deleteById(id))return true;
        return false;
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.update(doctor);
    }


    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
}
