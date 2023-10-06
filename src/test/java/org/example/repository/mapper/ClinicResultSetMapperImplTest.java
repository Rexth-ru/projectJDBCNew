package org.example.repository.mapper;

import org.example.model.Clinic;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class ClinicResultSetMapperImplTest {
@Mock
private ResultSet resultSet = Mockito.mock(ResultSet.class);
private final ClinicResultSetMapperImpl clinicResultSetMapper = new ClinicResultSetMapperImpl();
    @Test
    void map() throws SQLException {
        Mockito.when(resultSet.getLong("clinic_id")).thenReturn(Long.valueOf(1));
        Mockito.when(resultSet.getString("name_clinic")).thenReturn("Clinic");
        Clinic clinic = clinicResultSetMapper.map(resultSet);
        Assert.assertNotNull(clinic);
        Assert.assertEquals(Long.valueOf(1),clinic.getId());
        Assert.assertEquals("Clinic", clinic.getNameClinic());

    }
}