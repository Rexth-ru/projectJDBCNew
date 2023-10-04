package org.example.repository.mapper;

import org.example.model.Clinic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClinicResultSetMapperImpl implements GeneralResultSetMapper<Clinic> {
    @Override
    public Clinic map(ResultSet resultSet) throws SQLException {
        Clinic clinic = new Clinic();
        clinic.setId(resultSet.getLong("id"));
        clinic.setNameClinic(resultSet.getString("nameClinic"));
//        clinic.setDoctorList(resultSet.getObject("doctors",));

        return null;
    }
}
