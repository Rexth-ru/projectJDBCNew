package org.example.servlet.mapper;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutDoctorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DoctorDtoMapperImplTest {
    @InjectMocks
    private DoctorDtoMapperImpl doctorDtoMapper;
    @Test
    void mapToDoctor() {
        IncomingDoctorDto incomingDoctorDto =
                new IncomingDoctorDto(1L,"Айболит","доктор");
        Doctor doctor = doctorDtoMapper.map(incomingDoctorDto);
        Assertions.assertEquals(doctor.getNameDoctor(),incomingDoctorDto.getNameDoctor());
    }

    @Test
    void mapFromDoctor() {
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient(1L, "name");
        patients.add(patient);
        Doctor doctor = new Doctor(1L,"Айболит","доктор",patients);
        OutDoctorDto outDoctorDto = doctorDtoMapper.map(doctor);
        Assertions.assertEquals(outDoctorDto.getNameDoctor(),doctor.getNameDoctor());
    }
}