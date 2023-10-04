package org.example.servlet.mapper;

import org.example.model.Clinic;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.OutGoingClinicDto;

public class ClinicDtoMapperImpl implements ClinicDtoMapper{

    @Override
    public Clinic map(IncomingClinicDto incomingClinicDto) {
        Clinic clinic = new Clinic();
        clinic.setId(incomingClinicDto.getId());
        clinic.setNameClinic(incomingClinicDto.getNameClinic());

        return clinic;
    }

    @Override
    public OutGoingClinicDto map(Clinic clinic) {
        OutGoingClinicDto outGoingClinicDto = new OutGoingClinicDto();
        outGoingClinicDto.setId(clinic.getId());
        outGoingClinicDto.setNameClinic(clinic.getNameClinic());
        outGoingClinicDto.setDoctorList(clinic.getDoctorList());
        outGoingClinicDto.setPatientList(clinic.getPatientList());
        return outGoingClinicDto;
    }
}
