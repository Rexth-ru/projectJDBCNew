package org.example.repository.impl;

import org.example.db.DataSourceConnectHikari;
import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.repository.DoctorRepository;
import org.example.repository.mapper.GeneralResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {

    private final GeneralResultSetMapper<Doctor> doctorMapper;
    private final GeneralResultSetMapper<Clinic> clinicMapper;
    private final GeneralResultSetMapper<Patient> patientMapper;
    private final DataSourceConnectHikari connect;
    private static final String SQL_SELECT_ALL_DOCTORS = "select d.name_doctor, d.specialization, p.name_patient, c.name  from doctors as d\n" +
            "inner join doctor_patient dp on d.id = dp.doctor_id inner join patients p on p.id = dp.patient_id\n" +
            "inner join clinic c on c.id = d.clinic_id";
    private static final String SQL_SELECT_DOCTOR_ID =
            "select d.name_doctor, d.specialization, p.name_patient, c.name  from doctors as d\n" +
                    "inner join doctor_patient dp on d.id = dp.doctor_id inner join patients p on p.id = dp.patient_id\n" +
                    "inner join clinic c on c.id = d.clinic_id where d.id = ?";
    private static final String SQL_INSERT_DOCTOR =
            "INSERT INTO doctors (id, name_doctor, specialization, clinic_id) VALUES ((?),(?),(?),(?))";
    private static final String SQL_DELETE_DOCTOR_ID = "DELETE FROM doctors WHERE id =?";
    private static final String SQL_UPDATE_DOCTOR_ID =
            "UPDATE doctors SET name_doctor = ?, specialization = ? WHERE id = ? ";

    public DoctorRepositoryImpl(GeneralResultSetMapper<Doctor> doctorMapper, GeneralResultSetMapper<Clinic> clinicMapper, GeneralResultSetMapper<Patient> patientMapper, DataSourceConnectHikari connect) {
        this.doctorMapper = doctorMapper;
        this.clinicMapper = clinicMapper;
        this.patientMapper = patientMapper;

//        this.connect = connect;
        this.connect = connect;
    }

    @Override
    public Doctor findById(Long id) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DOCTOR_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Doctor doctor = doctorMapper.map(resultSet);
            Clinic clinic = clinicMapper.map(resultSet);
            doctor.setClinic(clinic);
            List<Patient> patients = new ArrayList<>();
            Patient patient = patientMapper.map(resultSet);
            patients.add(patient);
            while (resultSet.next()) {
                patients.add(patientMapper.map(resultSet));
            }
            doctor.setPatients(patients);

            return doctor;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_DOCTOR_ID)) {
            Integer resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0)
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.wasNull())
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = connect.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_DOCTORS);
            doctors.add(doctorMapper.map(rs));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return doctors;
    }

    @Override
    public Doctor save(Doctor doctor) {
        try (Connection connection = connect.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_INSERT_DOCTOR);
            Long id = resultSet.getLong(1);
            String nameDoctor = resultSet.getString(2);
            String specialization = resultSet.getString(3);

            return new Doctor(id, nameDoctor, specialization);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Doctor update(Doctor doctor) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SQL_UPDATE_DOCTOR_ID)) {
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.setString(2, doctor.getNameDoctor());
            preparedStatement.setString(3, doctor.getSpecialization());
            preparedStatement.executeUpdate();

//            doctor1.setId(doctorMapper.map(resultSet).getId());
//            doctor.setNameDoctor(doctor.getNameDoctor());
//            doctor1.setSpecialization(doctor.getSpecialization());

            return doctor;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
