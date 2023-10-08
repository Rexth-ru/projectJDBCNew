package org.example.servlet.dto;

import org.example.model.Doctor;
import org.example.model.Patient;

import java.util.List;

public class OutClinicDto {
     private String nameClinic;
    private List<String> patientsName;
    private List<String> doctorsName;
        public OutClinicDto() {
    }

    public OutClinicDto(String nameClinic, List<String> patientsName, List<String> doctorsName) {
        this.nameClinic = nameClinic;
        this.patientsName = patientsName;
        this.doctorsName = doctorsName;
    }

    public String getNameClinic() {
        return nameClinic;
    }

    public void setNameClinic(String nameClinic) {
        this.nameClinic = nameClinic;
    }

    public List<String> getPatientsName() {
        return patientsName;
    }

    public void setPatientsName(List<String> patientsName) {
        this.patientsName = patientsName;
    }

    public List<String> getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(List<String> doctorsName) {
        this.doctorsName = doctorsName;
    }
}
