package org.example.servlet.dto;

import java.util.List;
import java.util.Objects;

public class OutPatientDto {
    private String namePatient;
    private List<String> nameDoctors;

    public OutPatientDto(String namePatient) {
        this.namePatient = namePatient;
    }

    public OutPatientDto(String namePatient, List<String> nameDoctors) {
        this.namePatient = namePatient;
        this.nameDoctors = nameDoctors;
    }
    public OutPatientDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutPatientDto that = (OutPatientDto) o;
        return Objects.equals(namePatient, that.namePatient) && Objects.equals(nameDoctors, that.nameDoctors);
    }

    @Override
    public String toString() {
        return "OutPatientDto{" +
                "namePatient='" + namePatient + '\'' +
                ", nameDoctors=" + nameDoctors +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(namePatient, nameDoctors);
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
