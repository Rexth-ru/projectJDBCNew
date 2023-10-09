package org.example.repository.impl;

import com.zaxxer.hikari.HikariConfig;
import org.example.db.DataSourceConnectHikari;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class PatientRepositoryImplTest {
private PatientRepositoryImpl patientRepository;
    @Container
    public static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("aston")
            .withUsername("postgres")
            .withInitScript("db-migration.sql")
            .withPassword("469841");
    @BeforeAll
    static void setUp() {
        container.start();
    }
    @BeforeEach
    void setUpHikari(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(container.getJdbcUrl());
        hikariConfig.setPassword(container.getPassword());
        hikariConfig.setUsername(container.getUsername());
        patientRepository = new PatientRepositoryImpl(new DataSourceConnectHikari(hikariConfig));
    }
    @AfterAll
    static void stopContainer(){
        container.stop();
    }

    @Test
    void findById() throws ClassNotFoundException {
        Patient patient = patientRepository.findById(1L);
        Assertions.assertEquals(patient.getNamePatient(),"Морковкин");
    }

    @Test
    void deleteById() {
        Assertions.assertTrue(patientRepository.deleteById(2L));
    }

    @Test
    void save() throws SQLException {
        Patient patient = new Patient(6L,"namePatient");
        patientRepository.save(patient);
        Assertions.assertEquals(patient.getNamePatient(),"namePatient");
    }

    @Test
    void update() throws ClassNotFoundException {
        Patient patient = new Patient(3L,"namePatient");
        patientRepository.update(patient);
        Assertions.assertEquals(patient.getId(),3L);
        Assertions.assertEquals(patient.getNamePatient(),"namePatient");
    }
}