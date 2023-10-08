package org.example.repository.mapper;

import org.example.model.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientResultSetMapperImpl implements GeneralResultSetMapper<Patient> {
    @Override
    public Patient map(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong("patient_id"));
        patient.setNamePatient(resultSet.getString("name_patient"));
        return patient;
    }
}
