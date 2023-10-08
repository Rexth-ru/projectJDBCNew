package org.example.service.impl;

import org.example.model.Doctor;
import org.example.repository.impl.DoctorRepositoryImpl;
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
class DoctorServiceTest {
@Mock
private DoctorRepositoryImpl doctorRepository;
@InjectMocks
private DoctorService doctorService ;
private Doctor doctor;

@BeforeEach
void setUp(){
    doctorService = new DoctorService(doctorRepository);
    doctor = new Doctor(1L,"name","spec");

}
    @Test
    void save() throws SQLException {
        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
        Assertions.assertEquals(doctor,doctorService.save(doctor));
    }

    @Test
    void findById() {
    Mockito.when(doctorRepository.findById(1L)).thenReturn(doctor);
    Assertions.assertEquals(doctor, doctorService.findById(1L));
    }

    @Test
    void deleteById() {
    Mockito.when(doctorRepository.deleteById(2L)).thenReturn(true);
    Assertions.assertTrue(doctorService.deleteById(2L));
    }

    @Test
    void update() throws ClassNotFoundException {
    Mockito.when(doctorRepository.update(doctor)).thenReturn(doctor);
    Assertions.assertEquals(doctor, doctorService.update(doctor));
    }
}