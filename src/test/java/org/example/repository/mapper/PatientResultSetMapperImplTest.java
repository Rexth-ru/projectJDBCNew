package org.example.repository.mapper;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class PatientResultSetMapperImplTest {

    @Mock
    private ResultSet resultSet = Mockito.mock(ResultSet.class);
    private final PatientResultSetMapperImpl patientResultSetMapper = new PatientResultSetMapperImpl();

    @Test
    void map() throws SQLException {
        Mockito.when(resultSet.getLong("patient_id")).thenReturn(Long.valueOf(1));
        Mockito.when(resultSet.getString("name_patient")).thenReturn("namePatient");
        Patient patient = patientResultSetMapper.map(resultSet);
        Assert.assertNotNull(patient);
        Assert.assertEquals(Long.valueOf(1), patient.getId());
        Assert.assertEquals("namePatient", patient.getNamePatient());
    }
}