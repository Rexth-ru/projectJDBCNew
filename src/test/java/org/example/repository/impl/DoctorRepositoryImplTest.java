package org.example.repository.impl;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.*;

//@RunWith(MockitoJUnitRunner.class)

class DoctorRepositoryImplTest {
    private DoctorRepositoryImpl doctorRepository;
@Container
    public static final PostgreSQLContainer<?> container;

    static {
        container = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("test")
                .withUsername("test")
                .withInitScript("db-migration.sql")
                .withPassword("test");
    }

    @Mock
    private ResultSet resultSet = Mockito.mock(ResultSet.class);
//    @BeforeEach
//    void startContainer(){
//       container.start();
//    }
//    @AfterEach
//    void stopContainer(){
//        container.stop();
//    }
    @Test
    void findById() throws SQLException {
        Mockito.when(resultSet.getLong("doctor_id")).thenReturn(Long.valueOf(1));
        Mockito.when(resultSet.getString("name_doctor")).thenReturn("Иванов");
        Mockito.when(resultSet.getString("specialization")).thenReturn("Терапевт");
        Mockito.when(resultSet.getLong("patient_id")).thenReturn(Long.valueOf(4));
        Mockito.when(resultSet.getString("name_patient")).thenReturn("Капустин");
        Mockito.when(resultSet.getLong("clinic_id")).thenReturn(Long.valueOf(1));
        Mockito.when(resultSet.getString("name_clinic")).thenReturn("Городская поликлиника");
        try (Connection connection = DriverManager.getConnection(container.getJdbcUrl());
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select d.doctor_id, d.name_doctor,d.specialization," +
                     "p.patient_id, p.name_patient, c.clinic_id, c.name_clinic from doctors as d " +
                     "inner join doctor_patient dp on d.doctor_id = dp.doctor_id " +
                     "inner join patients p on p.patient_id = dp.patient_id " +
                     "inner join clinic c on c.doctor_id = d.doctor_id where d.doctor_id = 1 order by p.name_patient")){
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Assert.assertNotNull(rs);
            Assert.assertEquals(resultSet.getLong("doctor_id"), rs.getLong("doctor_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void deleteById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }
}