package org.example.model;

import java.util.List;
import java.util.Objects;

public class Clinic {
    private Long id;
    private String nameClinic;
    private List<Doctor> doctorList;

    public Clinic() {
    }

    public Clinic(Long id, String nameClinic) {
        this.id = id;
        this.nameClinic = nameClinic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameClinic() {
        return nameClinic;
    }

    public void setNameClinic(String nameClinic) {
        this.nameClinic = nameClinic;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clinic clinic = (Clinic) o;
        return Objects.equals(id, clinic.id) && Objects.equals(nameClinic, clinic.nameClinic) && Objects.equals(doctorList, clinic.doctorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameClinic, doctorList);
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id=" + id +
                ", nameClinic='" + nameClinic + '\'' +
                ", doctorList=" + doctorList +
                '}';
    }
}
