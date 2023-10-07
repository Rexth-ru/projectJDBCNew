package org.example.repository.impl;

import org.example.db.DataSourceConnectHikari;
import org.example.ex.ModelNotFoundException;
import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.repository.DoctorRepository;
import org.example.repository.mapper.ClinicResultSetMapperImpl;
import org.example.repository.mapper.DoctorResultSetMapperImpl;
import org.example.repository.mapper.GeneralResultSetMapper;
import org.example.repository.mapper.PatientResultSetMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {

    private GeneralResultSetMapper<Doctor> doctorMapper = new DoctorResultSetMapperImpl();
    private GeneralResultSetMapper<Clinic> clinicMapper = new ClinicResultSetMapperImpl();
    private GeneralResultSetMapper<Patient> patientMapper = new PatientResultSetMapperImpl();
    private DataSourceConnectHikari connect;

    public DoctorRepositoryImpl(DataSourceConnectHikari connect) {
        this.connect = connect;
    }

    public DoctorRepositoryImpl() {
        this.connect = new DataSourceConnectHikari();
    }

    //    private static final String SQL_SELECT_ALL_DOCTORS = "select d.name_doctor, d.specialization, p.name_patient, c.name  from doctors as d
//    inner join doctor_patient dp on d.id = dp.doctor_id inner join patients p on p.id = dp.patient_id
//    inner join clinic c on c.id = d.clinic_id";
    private static final String SQL_SELECT_DOCTOR_ID =
            "select d.doctor_id, d.name_doctor,d.specialization,\n" +
                    "p.patient_id, p.name_patient, c.clinic_id, c.name_clinic from doctors as d\n" +
                    "inner join doctor_patient dp on d.doctor_id = dp.doctor_id\n" +
                    "inner join patients p on p.patient_id = dp.patient_id\n" +
                    "inner join clinics c on c.doctor_id = d.doctor_id where d.doctor_id = ? order by p.name_patient";
    private static final String SQL_INSERT_DOCTOR =
            "INSERT INTO doctors (doctor_id, name_doctor, specialization) VALUES ((?),(?),(?))";
    private static final String SQL_DELETE_DOCTOR_ID = "DELETE FROM doctors WHERE doctor_id =?";
    private static final String SQL_UPDATE_DOCTOR_ID =
            "UPDATE doctors SET name_doctor = ?, specialization = ? WHERE doctor_id = ? ";


    @Override
    public Doctor findById(Long id) {

        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DOCTOR_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Doctor doctor;
            List<Patient> patients = new ArrayList<>();
            if (resultSet.next()) {
                doctor = doctorMapper.map(resultSet);
                Patient patient = patientMapper.map(resultSet);
                patients.add(patient);
                while (resultSet.next()) {
                    patient = patientMapper.map(resultSet);
                    patients.add(patient);
                }
                doctor.setPatients(patients);
                return doctor;
            } else throw new ModelNotFoundException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_DOCTOR_ID)) {
            preparedStatement.setLong(1, id);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) throw new ModelNotFoundException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Doctor save(Doctor doctor) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DOCTOR)) {
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.setString(2, doctor.getNameDoctor());
            preparedStatement.setString(3, doctor.getSpecialization());
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) throw new ModelNotFoundException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctor;
    }

    @Override
    public Doctor update(Doctor doctor) {
            Doctor doctorOld = findById(doctor.getId());
            if (doctorOld!=null) {
                doctorOld.setNameDoctor(doctor.getNameDoctor());
                doctorOld.setSpecialization(doctor.getSpecialization());
                return doctorOld;
            }throw new RuntimeException();
    }

    @Override
    public List<Doctor> findAll() {
//        List<Doctor> doctors = new ArrayList<>();
//        List<Patient> patients = new ArrayList<>();
//        try (Connection connection = connect.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_DOCTORS)) {
//            ResultSet rs = statement.executeQuery();
//           rs.next();
//           doctors.add(doctorMapper.map(rs));
//            Patient patient = patientMapper.map(rs);
//            doctors.get(0).setPatients();
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        return doctors;
        return null;
    }}
