package org.example.servlet.mapper;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.servlet.dto.IncomingPatientDTO;
import org.example.servlet.dto.OutPatientDto;

public class PatientDtoMapperImpl implements PatientDtoMapper {
    @Override
    public Patient map(IncomingPatientDTO incomingPatientDTO) {
        Patient patient = new Patient();
        patient.setId(incomingPatientDTO.getId());
        patient.setNamePatient(incomingPatientDTO.getName());
        return patient;
    }
    @Override
    public OutPatientDto map(Patient patient) {
        OutPatientDto outPatientDto = new OutPatientDto();
        outPatientDto.setNamePatient(patient.getNamePatient());
        outPatientDto.setNameDoctors(patient.getDoctors().stream().map(Doctor::getNameDoctor).toList());
        return outPatientDto;
    }
}
