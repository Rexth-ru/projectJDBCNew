package org.example.servlet.dto;

import java.util.Objects;
import java.util.UUID;

public class IncomingClinicDto {
    private Long id;
    private String nameClinic;

    public IncomingClinicDto() {
    }

    public IncomingClinicDto(Long id, String nameClinic) {
        this.id = id;
        this.nameClinic = nameClinic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingClinicDto that = (IncomingClinicDto) o;
        return Objects.equals(id, that.id) && Objects.equals(nameClinic, that.nameClinic);
    }

    @Override
    public String toString() {
        return "IncomingClinicDto{" +
                "id=" + id +
                ", nameClinic='" + nameClinic + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameClinic);
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
}
