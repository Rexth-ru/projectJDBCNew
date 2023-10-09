package org.example.service.impl;

import org.example.model.Patient;
import org.example.repository.impl.PatientRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientRepositoryImpl patientRepository;
    @InjectMocks
    private PatientService patientService ;
@BeforeEach
void setUp(){
    patientService = new PatientService(patientRepository);
}

    @Test
    void save() throws SQLException {
        Patient patient = new Patient(1L, "Черепашкин", null);
        Mockito.when(patientRepository.save(patient)).thenReturn(patient);
        Assertions.assertEquals(patient, patientRepository.save(patient));

    }

    @Test
    void findById() throws ClassNotFoundException {
        Patient patient = new Patient(1L, "Черепашкин", null);
        Mockito.when(patientRepository.findById(1L)).thenReturn(patient);
        Assertions.assertEquals(patient, patientService.findById(1L));
    }

    @Test
    void deleteById() {
        Mockito.when(patientRepository.deleteById(1L)).thenReturn(true);
        Assertions.assertTrue(patientService.deleteById(1L));
    }

    @Test
    void update() throws ClassNotFoundException {
        Patient patient1 = new Patient(1L, "Котиков", null);
        Mockito.when(patientRepository.update(patient1)).thenReturn(patient1);
        Assertions.assertEquals(patient1, patientService.update(patient1));
    }
}