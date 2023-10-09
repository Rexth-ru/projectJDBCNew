package org.example.repository.mapper;

import org.example.model.Doctor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
class DoctorResultSetMapperImplTest {
@Mock
private ResultSet resultSet = Mockito.mock(ResultSet.class);

private final DoctorResultSetMapperImpl doctorResultSetMapper = new DoctorResultSetMapperImpl();

    @Test
    void map() throws SQLException {
        Mockito.when(resultSet.getLong("doctor_id")).thenReturn(Long.valueOf(1));
        Mockito.when(resultSet.getString("name_doctor")).thenReturn("nameDoctor");
        Mockito.when(resultSet.getString("specialization")).thenReturn("doctor");

        Doctor doctor = doctorResultSetMapper.map(resultSet);
        Assert.assertNotNull(doctor);
        Assert.assertEquals(Long.valueOf(1), doctor.getId());
        Assert.assertEquals("nameDoctor", doctor.getNameDoctor());
        Assert.assertEquals("doctor", doctor.getSpecialization());

    }
}