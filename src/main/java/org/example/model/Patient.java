package org.example.model;

import java.util.List;
import java.util.Objects;

public class Patient {
    private Long id;
    private String namePatient;
    private Clinic clinic;

    private List<Doctor> doctors;

    public Patient() {
    }

    public Patient(Long id, String namePatient, Clinic clinic, List<Doctor> doctors) {
        this.id = id;
        this.namePatient = namePatient;
        this.clinic = clinic;
        this.doctors = doctors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(namePatient, patient.namePatient) && Objects.equals(clinic, patient.clinic) && Objects.equals(doctors, patient.doctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePatient, clinic, doctors);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", namePatient='" + namePatient + '\'' +
                ", clinic=" + clinic +
                ", doctors=" + doctors +
                '}';
    }
}
