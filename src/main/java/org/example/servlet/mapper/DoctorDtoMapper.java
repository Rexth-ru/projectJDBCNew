package org.example.servlet.mapper;

import org.example.model.Doctor;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutDoctorDto;

public interface DoctorDtoMapper {
    Doctor map(IncomingDoctorDto incomingDoctorDto);
    OutDoctorDto map(Doctor doctor);

}
