package org.example.servlet.dto;

import java.util.List;
import java.util.Objects;

public class OutDoctorDto {
    private String nameDoctor;
    private String specialization;
    private List<String> patientName;
    public OutDoctorDto() {
    }

    public OutDoctorDto(String nameDoctor, String specialization) {
        this.nameDoctor = nameDoctor;
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutDoctorDto that = (OutDoctorDto) o;
        return Objects.equals(nameDoctor, that.nameDoctor) && Objects.equals(specialization, that.specialization) && Objects.equals(patientName, that.patientName);
    }

    @Override
    public String toString() {
        return "OutDoctorDto{" +
                "nameDoctor='" + nameDoctor + '\'' +
                ", specialization='" + specialization + '\'' +
                ", patientName=" + patientName +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameDoctor, specialization, patientName);
    }

    public OutDoctorDto(String nameDoctor, String specialization, List<String> patientName) {
        this.nameDoctor = nameDoctor;
        this.specialization = specialization;
        this.patientName = patientName;
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

    public List<String> getPatientName() {
        return patientName;
    }

    public void setPatientName(List<String> patientName) {
        this.patientName = patientName;
    }
}



//    ResultSetMetaData md = resultSet.getMetaData();
//    int numCols = md.getColumnCount();
//    List<String> colNames = IntStream.range(0, numCols)
//            .mapToObj(i -> {
//                try {
//                    return md.getColumnName(i + 1);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    return "?";
//                }
//            })
//            .collect(Collectors.toList());
//    List json = DSL.using(dbConnection)
//            .fetch(resultSet)
//            .map(new RecordMapper() {
//                @Override
//                public JSONObject map(Record r) {
//                    JSONObject obj = new JSONObject();
//                    colNames.forEach(cn -> obj.put(cn, r.get(cn)));
//                    return obj;
//                }
//            });
//return new JSONArray(json);
