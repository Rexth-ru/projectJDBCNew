package org.example.servlet.mapper;

import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.OutClinicDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class ClinicDtoMapperImplTest {
    @InjectMocks
    private ClinicDtoMapperImpl clinicDtoMapper;

    @Test
    void mapToClinic() {
        IncomingClinicDto incomingClinicDto = new IncomingClinicDto(1L, "clinic");
        Clinic clinic = clinicDtoMapper.map(incomingClinicDto);
        Assertions.assertEquals(clinic.getNameClinic(), incomingClinicDto.getNameClinic());
    }

    @Test
    void mapFromClinic() {
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient(1L, "name");
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor = new Doctor(1L, "Айболит", "доктор", patients);
        doctorList.add(doctor);
        patients.add(patient);
        Clinic clinic = new Clinic(1L, "clinic", doctorList);
        OutClinicDto outClinicDto = clinicDtoMapper.map(clinic);
        Assertions.assertEquals(clinic.getNameClinic(), outClinicDto.getNameClinic());
    }
}