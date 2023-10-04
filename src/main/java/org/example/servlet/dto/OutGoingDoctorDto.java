package org.example.servlet.dto;

import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;

import java.util.List;

public class OutGoingDoctorDto {
    private String nameDoctor;
    private String specialization;
    private List<String> patients;
    private String clinic;

    public OutGoingDoctorDto() {
    }

    public OutGoingDoctorDto(String nameDoctor, String specialization, List<String> patients, String clinic) {
        this.nameDoctor = nameDoctor;
        this.specialization = specialization;
        this.patients = patients;
        this.clinic = clinic;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }}

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
