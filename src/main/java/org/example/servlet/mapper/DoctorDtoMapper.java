package org.example.servlet.mapper;

import org.example.model.Doctor;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutGoingDoctorDto;

public interface DoctorDtoMapper {
    Doctor map(IncomingDoctorDto incomingDoctorDto);
    OutGoingDoctorDto map(Doctor doctor);


}
