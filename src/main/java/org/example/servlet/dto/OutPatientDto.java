package org.example.servlet.dto;

import java.util.List;

public class OutPatientDto {
    private String namePatient;
    private List<String> nameDoctors;

    public OutPatientDto(String namePatient, List<String> nameDoctors) {
        this.namePatient = namePatient;
        this.nameDoctors = nameDoctors;
    }

    public OutPatientDto() {
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public List<String> getNameDoctors() {
        return nameDoctors;
    }

    public void setNameDoctors(List<String> nameDoctors) {
        this.nameDoctors = nameDoctors;
    }
}
