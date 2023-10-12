package org.example.servlet.dto;

import java.util.List;

public class OutClinicDto {
    private String nameClinic;
    private List<String> patientsName;
    private List<String> doctorsName;

    public OutClinicDto() {
    }

    public OutClinicDto(String nameClinic) {
        this.nameClinic = nameClinic;
    }


    public String getNameClinic() {
        return nameClinic;
    }

    public void setNameClinic(String nameClinic) {
        this.nameClinic = nameClinic;
    }

    public void setPatientsName(List<String> patientsName) {
        this.patientsName = patientsName;
    }

    public void setDoctorsName(List<String> doctorsName) {
        this.doctorsName = doctorsName;
    }
}
