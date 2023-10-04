package org.example.servlet.mapper;

import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutGoingDoctorDto;

import java.util.List;
import java.util.stream.Collectors;

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
    public OutGoingDoctorDto map(Doctor doctor) {
        OutGoingDoctorDto outGoingDoctorDto = new OutGoingDoctorDto();
        outGoingDoctorDto.setNameDoctor(doctor.getNameDoctor());
        outGoingDoctorDto.setSpecialization(doctor.getSpecialization());
        outGoingDoctorDto.setClinic(doctor.getClinic().getNameClinic());
        outGoingDoctorDto.setPatients(doctor.getPatients().stream().map(Patient::getNamePatient).toList());
        return outGoingDoctorDto;
    }


}
