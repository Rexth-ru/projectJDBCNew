package org.example.repository.mapper;

import org.example.model.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorResultSetMapperImpl implements GeneralResultSetMapper<Doctor> {

    @Override
    public Doctor map(ResultSet resultSet) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(resultSet.getLong("doctor_id"));
        doctor.setNameDoctor(resultSet.getString("name_doctor"));
        doctor.setSpecialization(resultSet.getString("specialization"));
        return doctor;
    }
}
