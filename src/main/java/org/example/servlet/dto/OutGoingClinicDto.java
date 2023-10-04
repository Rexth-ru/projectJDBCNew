package org.example.servlet.dto;

import org.example.model.Doctor;
import org.example.model.Patient;

import java.util.List;

public class OutGoingClinicDto {
    private Long id;
    private String nameClinic;
    private List<Patient> patientList;
    private List<Doctor> doctorList;

    public OutGoingClinicDto(Long id, String nameClinic, List<Patient> patientList, List<Doctor> doctorList) {
        this.id = id;
        this.nameClinic = nameClinic;
        this.patientList = patientList;
        this.doctorList = doctorList;
    }

    public OutGoingClinicDto() {
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

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

}
