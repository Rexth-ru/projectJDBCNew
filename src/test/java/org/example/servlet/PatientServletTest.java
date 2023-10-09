package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.service.impl.DoctorService;
import org.example.service.impl.PatientService;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.IncomingPatientDTO;
import org.example.servlet.dto.OutClinicDto;
import org.example.servlet.dto.OutPatientDto;
import org.example.servlet.mapper.DoctorDtoMapper;
import org.example.servlet.mapper.PatientDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PatientServletTest {
    @InjectMocks
    private PatientServlet patientServlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PatientService patientService;
    @Mock
    private PrintWriter writer;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private PatientDtoMapper patientDtoMapper;
    @Mock
    private ObjectMapper objectMapper;
    private Patient patient;
    private Doctor doctor;
    @BeforeEach
    void setUp(){
        patientServlet = new PatientServlet(patientService,patientDtoMapper, objectMapper);
        doctor = new Doctor(1L,"name","doctor");
        patient = new Patient(1L,"patient",List.of(doctor));
    }
    @Test
    void doGet() throws IOException, ClassNotFoundException {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(1));
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(patientService.findById(1L)).thenReturn(patient);

        patientServlet.doGet(request,response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
        Mockito.verify(patientDtoMapper, Mockito.times(1)).map(patient);
    }

    @Test
    void doGetBad() throws IOException {
        Mockito.when(request.getParameter("id")).thenReturn("");
        patientServlet.doGet(request,response);
        Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void doPost() throws IOException, SQLException {
        IncomingPatientDTO incomingPatientDTO = new IncomingPatientDTO(2L, "name");
        Patient patient1 = new Patient(2L, "name");
        OutPatientDto outPatientDto = new OutPatientDto(patient1.getNamePatient());
        String reqBody = "{\"id\": \"2\" ,\"namePatient\":\"name\"}";
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(request.getReader()).thenReturn(bufferedReader);
        Mockito.when(bufferedReader.readLine()).thenReturn(reqBody, null);
        Mockito.when(objectMapper.readValue(reqBody, IncomingPatientDTO.class)).thenReturn(incomingPatientDTO);
        Mockito.when(patientDtoMapper.map(incomingPatientDTO)).thenReturn(patient1);
        Mockito.when(patientService.save(patient1)).thenReturn(patient1);
        Mockito.when(patientDtoMapper.map(patient1)).thenReturn(outPatientDto);
        objectMapper.writeValueAsString(outPatientDto);

        patientServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
    }
    @Test
    void doPut() throws IOException, ClassNotFoundException {
        IncomingPatientDTO incomingPatientDTO = new IncomingPatientDTO(1L, "name");
        Patient patient1 = new Patient(1L, "name");
        OutPatientDto outPatientDto = new OutPatientDto(patient1.getNamePatient());
        String reqBody = "{\"id\": \"1\" ,\"namePatient\":\"name\"}";
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(request.getReader()).thenReturn(bufferedReader);
        Mockito.when(bufferedReader.readLine()).thenReturn(reqBody, null);
        Mockito.when(objectMapper.readValue(reqBody, IncomingPatientDTO.class)).thenReturn(incomingPatientDTO);
        Mockito.when(patientDtoMapper.map(incomingPatientDTO)).thenReturn(patient1);
        Mockito.when(patientService.update(patient1)).thenReturn(patient1);
        Mockito.when(patientDtoMapper.map(patient1)).thenReturn(outPatientDto);
        objectMapper.writeValueAsString(outPatientDto);

        patientServlet.doPut(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
    }
    @Test
    void doDelete() {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(1));
        Mockito.when(patientService.deleteById(1L)).thenReturn(true);

        patientServlet.doDelete(request,response);

        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);

    }
    @Test
    void doDeleteBad(){
        Mockito.when(request.getParameter("id")).thenReturn("");
        patientServlet.doDelete(request,response);
        Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }
}