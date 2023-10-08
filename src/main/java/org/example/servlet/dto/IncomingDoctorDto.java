package org.example.servlet.dto;

public class IncomingDoctorDto {
    private Long id;
    private String nameDoctor;
    private String specialization;

    public IncomingDoctorDto(Long id, String nameDoctor, String specialization) {
        this.id = id;
        this.nameDoctor = nameDoctor;
        this.specialization = specialization;
    }

    public IncomingDoctorDto() {
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
}
