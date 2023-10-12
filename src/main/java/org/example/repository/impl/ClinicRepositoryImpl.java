package org.example.repository.impl;

import org.example.db.DataSourceConnectHikari;
import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.repository.ClinicRepository;
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

public class ClinicRepositoryImpl implements ClinicRepository {
    private GeneralResultSetMapper<Doctor> doctorMapper = new DoctorResultSetMapperImpl();
    private GeneralResultSetMapper<Patient> patientMapper = new PatientResultSetMapperImpl();
    private GeneralResultSetMapper<Clinic> clinicMapper = new ClinicResultSetMapperImpl();
    private DataSourceConnectHikari connect;

    public ClinicRepositoryImpl() {
        this.connect =new DataSourceConnectHikari();
    }
    public ClinicRepositoryImpl(DataSourceConnectHikari connect) {
        this.connect = connect;
    }

    private final String SQL_SELECT_CLINIC_ID =
            "SELECT c.clinic_id, c.name_clinic, d.doctor_id, d.name_doctor, d.specialization, p.patient_id, p.name_patient\n" +
                    "from clinics as c\n" +
                    "inner join doctors d on d.doctor_id = c.doctor_id\n" +
                    "inner join doctor_patient dp on c.doctor_id = dp.doctor_id\n" +
                    "inner join patients p on p.patient_id = dp.patient_id where c.clinic_id = ? order by d.name_doctor";
    private final String SQL_INSERT_CLINIC =
            "INSERT INTO clinics (clinic_id, name_clinic) VALUES ((?),(?))";
    private final String SQL_DELETE_CLINIC_ID =
            "DELETE FROM clinics WHERE clinic_id =?";

    @Override
    public Clinic findById(Long id) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLINIC_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Clinic clinic = null;
            if (resultSet.next()) {
                List<Doctor> doctors = new ArrayList<>();
                List<Patient> patients = new ArrayList<>();
                Doctor doctor = doctorMapper.map(resultSet);
                Patient patient = patientMapper.map(resultSet);
                clinic = clinicMapper.map(resultSet);
                patients.add(patient);
                doctor.setPatients(patients);
                doctors.add(doctor);
                while (resultSet.next()) {
                    if (resultSet.getLong("doctor_id") == doctors.get(doctors.size() - 1).getId()) {
                        patient = patientMapper.map(resultSet);
                        patients.add(patient);
                        doctor.setPatients(patients);
                    } else {

                        List<Patient> patients1 = new ArrayList<>();
                        Doctor doctorOther = doctorMapper.map(resultSet);
                        Patient patient1 = patientMapper.map(resultSet);
                        patients1.add(patient1);
                        doctorOther.setPatients(patients1);
                        doctors.add(doctorOther);
                    }
                }
                clinic.setDoctorList(doctors);
            }
            return clinic;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CLINIC_ID)) {
            preparedStatement.setLong(1, id);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Clinic save(Clinic clinic) {

        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_CLINIC)) {
            preparedStatement.setLong(1, clinic.getId());
            preparedStatement.setString(2, clinic.getNameClinic());

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet == 0) throw new RuntimeException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clinic;
    }

    @Override
    public Clinic update(Clinic clinic) {
        Clinic clinicOld = findById(clinic.getId());
        if (clinicOld != null) {
            clinicOld.setNameClinic(clinic.getNameClinic());
            return clinicOld;
        }
        return null;
    }
}
