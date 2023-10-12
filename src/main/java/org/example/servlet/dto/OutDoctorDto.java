package org.example.servlet.dto;

import java.util.List;

public class OutDoctorDto {
    private String nameDoctor;
    private String specialization;
    private List<String> patientName;

    public OutDoctorDto() {
    }

    public OutDoctorDto(String nameDoctor, String specialization) {
        this.nameDoctor = nameDoctor;
        this.specialization = specialization;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPatientName(List<String> patientName) {
        this.patientName = patientName;
    }
}



