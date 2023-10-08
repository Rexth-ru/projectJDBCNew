package org.example.servlet.mapper;

import org.example.model.Patient;
import org.example.servlet.dto.IncomingPatientDTO;
import org.example.servlet.dto.OutPatientDto;

public interface PatientDtoMapper {
    Patient map(IncomingPatientDTO incomingPatientDTO);

    OutPatientDto map(Patient patient);
}
