package org.example.servlet.dto;

public class IncomingPatientDTO {
    private Long id;
    private String name;

    public IncomingPatientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
