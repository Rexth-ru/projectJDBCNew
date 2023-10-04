package org.example.model;

import java.util.List;
import java.util.Objects;

public class Doctor {
    private Long id;
    private String nameDoctor;
    private String specialization;
    private List<Patient> patients;
    private Clinic clinic;

    public Doctor() {
    }

    public Doctor(Long id, String nameDoctor, String specialization) {
        this.id = id;
        this.nameDoctor = nameDoctor;
        this.specialization = specialization;
    }

    public Doctor(Long id, String nameDoctor, String specialization, Clinic clinic) {
        this.id = id;
        this.nameDoctor = nameDoctor;
        this.specialization = specialization;
        this.clinic = clinic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(nameDoctor, doctor.nameDoctor) && Objects.equals(specialization, doctor.specialization) && Objects.equals(patients, doctor.patients) && Objects.equals(clinic, doctor.clinic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDoctor, specialization, patients, clinic);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", nameDoctor='" + nameDoctor + '\'' +
                ", specialization='" + specialization + '\'' +
                ", patients=" + patients +
                ", clinic=" + clinic +
                '}';
    }
}
