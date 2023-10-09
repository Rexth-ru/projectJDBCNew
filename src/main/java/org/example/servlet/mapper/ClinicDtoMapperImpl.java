package org.example.servlet.mapper;

import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.OutClinicDto;

public class ClinicDtoMapperImpl implements ClinicDtoMapper {

    @Override
    public Clinic map(IncomingClinicDto incomingClinicDto) {
        Clinic clinic = new Clinic();
        clinic.setId(incomingClinicDto.getId());
        clinic.setNameClinic(incomingClinicDto.getNameClinic());
        return clinic;
    }
    @Override
    public OutClinicDto map(Clinic clinic) {
        OutClinicDto outClinicDto = new OutClinicDto();
        outClinicDto.setNameClinic(clinic.getNameClinic());
        outClinicDto.setDoctorsName(clinic.getDoctorList().stream().map(Doctor::getNameDoctor).toList());
        outClinicDto.setPatientsName(clinic.getDoctorList().stream()
                .flatMap((Doctor doctor) -> doctor.getPatients().stream().map(Patient::getNamePatient)).toList());
        return outClinicDto;
    }
}
