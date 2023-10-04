package org.example.servlet.mapper;

import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutGoingClinicDto;
import org.example.servlet.dto.OutGoingDoctorDto;

public interface ClinicDtoMapper {
    Clinic map(IncomingClinicDto incomingClinicDto);
    OutGoingClinicDto map(Clinic clinic);
}
