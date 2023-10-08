package org.example.repository.impl;

import org.example.db.DataSourceConnectHikari;
import org.example.ex.ModelNotFoundException;
import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.repository.PatientRepository;
import org.example.repository.mapper.ClinicResultSetMapperImpl;
import org.example.repository.mapper.DoctorResultSetMapperImpl;
import org.example.repository.mapper.GeneralResultSetMapper;
import org.example.repository.mapper.PatientResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {
    private GeneralResultSetMapper<Doctor> doctorMapper = new DoctorResultSetMapperImpl();
    private GeneralResultSetMapper<Patient> patientMapper = new PatientResultSetMapperImpl();
    private GeneralResultSetMapper<Clinic> clinicMapper = new ClinicResultSetMapperImpl();
    private DataSourceConnectHikari connect;
    private final String SQL_SELECT_PATIENT_ID="select p.patient_id, p.name_patient, d.doctor_id, d.name_doctor, d.specialization from patients p\n" +
            "inner join doctor_patient dp on p.patient_id = dp.patient_id\n" +
            "inner join doctors d on d.doctor_id = dp.doctor_id where p.patient_id = ? order by d.name_doctor";
    private static final String SQL_INSERT_PATIENT =
            "INSERT INTO patients (patient_id, name_patient) VALUES ((?),(?))";
    private static final String SQL_DELETE_PATIENT_ID = "DELETE FROM patients WHERE patient_id =?";


    public PatientRepositoryImpl(DataSourceConnectHikari connect) {
        this.connect = connect;
    }

    @Override
    public Patient findById(Long id) throws ClassNotFoundException {
            try (Connection connection = connect.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PATIENT_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                Patient patient;
                List<Doctor> doctors = new ArrayList<>();
                if (resultSet.next()) {
                    patient = patientMapper.map(resultSet);
                    Doctor doctor = doctorMapper.map(resultSet);
                    doctors.add(doctor);
                    while (resultSet.next()) {
                        doctor = doctorMapper.map(resultSet);
                        doctors.add(doctor);
                    }
                    patient.setDoctors(doctors);
                    return patient;
                } else throw new ModelNotFoundException();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        @Override
    public boolean deleteById(Long id){
        try (Connection connection = connect.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PATIENT_ID)) {
        preparedStatement.setLong(1, id);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet == 0) throw new ModelNotFoundException();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return true;
}

    @Override
    public Patient save(Patient patient) throws SQLException {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PATIENT)) {
            preparedStatement.setLong(1, patient.getId());
            preparedStatement.setString(2, patient.getNamePatient());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) throw new ModelNotFoundException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public Patient update(Patient patient) throws ClassNotFoundException {
        Patient patientOld = findById(patient.getId());
        if (patientOld != null) {
            patientOld.setNamePatient(patient.getNamePatient());
            return patientOld;
        }
        throw new RuntimeException();
    }
}
