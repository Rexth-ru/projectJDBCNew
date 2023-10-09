package org.example.servlet.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingDoctorDto that = (IncomingDoctorDto) o;
        return Objects.equals(id, that.id) && Objects.equals(nameDoctor, that.nameDoctor) && Objects.equals(specialization, that.specialization);
    }

    @Override
    public String toString() {
        return "IncomingDoctorDto{" +
                "id=" + id +
                ", nameDoctor='" + nameDoctor + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nameDoctor, specialization);
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
