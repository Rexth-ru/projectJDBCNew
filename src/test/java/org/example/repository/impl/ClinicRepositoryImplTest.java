package org.example.repository.impl;

import com.zaxxer.hikari.HikariConfig;
import org.example.db.DataSourceConnectHikari;
import org.example.model.Clinic;
import org.example.model.Doctor;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.*;

class ClinicRepositoryImplTest {
   private ClinicRepositoryImpl clinicRepository;
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
        clinicRepository = new ClinicRepositoryImpl(new DataSourceConnectHikari(hikariConfig));
    }
    @AfterAll
    static void stopContainer(){
        container.stop();
    }
    @Test
    void findById() {
        Clinic clinic = clinicRepository.findById(1L);
        Assertions.assertEquals(clinic.getNameClinic(),"Городская поликлиника");
    }

    @Test
    void deleteById() {
        Assertions.assertTrue(clinicRepository.deleteById(2L));
    }

    @Test
    void save() {
        Clinic clinic = new Clinic(5L,"nameClinic");
        clinicRepository.save(clinic);
        Assertions.assertEquals(clinic.getNameClinic(),"nameClinic");
    }

    @Test
    void update() {
        Clinic clinic = new Clinic(1L,"nameClinic");
        clinicRepository.update(clinic);
        Assertions.assertEquals(clinic.getId(),1L);
        Assertions.assertEquals(clinic.getNameClinic(),"nameClinic");
    }
}