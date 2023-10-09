package org.example.servlet.dto;

import org.example.model.Doctor;
import org.example.model.Patient;

import java.util.List;
import java.util.Objects;

public class OutClinicDto {
     private String nameClinic;
    private List<String> patientsName;
    private List<String> doctorsName;
        public OutClinicDto() {
    }

    public OutClinicDto(String nameClinic) {
        this.nameClinic = nameClinic;
    }

    public OutClinicDto(String nameClinic, List<String> patientsName, List<String> doctorsName) {
        this.nameClinic = nameClinic;
        this.patientsName = patientsName;
        this.doctorsName = doctorsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutClinicDto that = (OutClinicDto) o;
        return Objects.equals(nameClinic, that.nameClinic) && Objects.equals(patientsName, that.patientsName) && Objects.equals(doctorsName, that.doctorsName);
    }

    @Override
    public String toString() {
        return "OutClinicDto{" +
                "nameClinic='" + nameClinic + '\'' +
                ", patientsName=" + patientsName +
                ", doctorsName=" + doctorsName +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameClinic, patientsName, doctorsName);
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
