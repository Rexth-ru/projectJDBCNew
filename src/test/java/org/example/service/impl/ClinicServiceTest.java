package org.example.service.impl;

import org.example.model.Clinic;
import org.example.repository.impl.ClinicRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClinicServiceTest {
    @Mock
    private ClinicRepositoryImpl clinicRepository;
    @InjectMocks
    private ClinicService clinicService;
    private Clinic clinic;

    @BeforeEach
    void setUp() {
        clinicService = new ClinicService(clinicRepository);
        clinic = new Clinic(1L, "clinica", null);
    }

    @Test
    void save() throws SQLException {
        Mockito.when(clinicRepository.save(clinic)).thenReturn(clinic);
        Assertions.assertEquals(clinic, clinicService.save(clinic));
    }

    @Test
    void findById() throws ClassNotFoundException {
        Mockito.when(clinicRepository.findById(1L)).thenReturn(clinic);
        Assertions.assertEquals(clinic, clinicService.findById(1L));
    }

    @Test
    void deleteById() {
        Mockito.when(clinicRepository.deleteById(2L)).thenReturn(true);
        Assertions.assertTrue(clinicService.deleteById(2L));
    }

    @Test
    void update() throws ClassNotFoundException {
        Mockito.when(clinicRepository.update(clinic)).thenReturn(clinic);
        Assertions.assertEquals(clinic, clinicService.update(clinic));
    }
}