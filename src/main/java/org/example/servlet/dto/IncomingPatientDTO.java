package org.example.servlet.dto;

import java.util.Objects;

public class IncomingPatientDTO {
    private Long id;
    private String name;

    public IncomingPatientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public IncomingPatientDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingPatientDTO that = (IncomingPatientDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "IncomingPatientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
