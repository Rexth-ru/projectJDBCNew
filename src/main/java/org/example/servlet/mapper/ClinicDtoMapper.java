package org.example.servlet.mapper;

import org.example.model.Clinic;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.OutClinicDto;

public interface ClinicDtoMapper {
    Clinic map(IncomingClinicDto incomingClinicDto);
    OutClinicDto map(Clinic clinic);
}
