package org.example.service.impl;

import org.example.model.Clinic;
import org.example.repository.ClinicRepository;
import org.example.repository.impl.ClinicRepositoryImpl;
import org.example.service.MyService;

import java.sql.SQLException;

public class ClinicService implements MyService<Clinic> {
    private  final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Clinic save(Clinic clinic) throws SQLException {
       return clinicRepository.save(clinic);
    }

    @Override
    public Clinic findById(Long id) throws ClassNotFoundException {
        return clinicRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return clinicRepository.deleteById(id);
    }

    @Override
    public Clinic update(Clinic clinic) throws ClassNotFoundException {
        return clinicRepository.update(clinic);
    }

}
