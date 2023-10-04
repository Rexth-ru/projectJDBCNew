package org.example.servlet.dto;

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
