package org.example.servlet.mapper;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutDoctorDto;

public class DoctorDtoMapperImpl implements DoctorDtoMapper {
    @Override
    public Doctor map(IncomingDoctorDto incomingDoctorDto) {
        Doctor doctor = new Doctor();
        doctor.setId(incomingDoctorDto.getId());
        doctor.setNameDoctor(incomingDoctorDto.getNameDoctor());
        doctor.setSpecialization(incomingDoctorDto.getSpecialization());
        return doctor;
    }

    @Override
    public OutDoctorDto map(Doctor doctor) {
        OutDoctorDto outDoctorDto = new OutDoctorDto();
        outDoctorDto.setNameDoctor(doctor.getNameDoctor());
        outDoctorDto.setSpecialization(doctor.getSpecialization());
        outDoctorDto.setPatientName(doctor.getPatients().stream().map(Patient::getNamePatient).toList());
        return outDoctorDto;
    }
}
