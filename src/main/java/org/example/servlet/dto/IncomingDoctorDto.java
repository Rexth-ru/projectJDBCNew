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

    public Long getId() {
        return id;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public String getSpecialization() {
        return specialization;
    }

}
