package org.example.servlet.mapper;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.servlet.dto.IncomingPatientDTO;
import org.example.servlet.dto.OutDoctorDto;
import org.example.servlet.dto.OutPatientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PatientDtoMapperImplTest {
@InjectMocks
private PatientDtoMapperImpl patientDtoMapper;
    @Test
    void mapToPatient() {
        IncomingPatientDTO incomingPatientDTO = new IncomingPatientDTO(1L,"name");
        Patient patient = patientDtoMapper.map(incomingPatientDTO);
        Assertions.assertEquals(patient.getNamePatient(),incomingPatientDTO.getName());
    }

    @Test
    void mapFromPatient() {
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor = new Doctor(1L, "Айболит", "доктор");
        doctorList.add(doctor);
        Patient patient = new Patient(1L,"name",doctorList);
        OutPatientDto outPatientDto = patientDtoMapper.map(patient);
        Assertions.assertEquals(outPatientDto.getNamePatient(),patient.getNamePatient());
    }
}