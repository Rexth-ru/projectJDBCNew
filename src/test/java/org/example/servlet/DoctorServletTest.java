package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.service.impl.DoctorService;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutDoctorDto;
import org.example.servlet.mapper.DoctorDtoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
class DoctorServletTest {
    @InjectMocks
    private DoctorServlet doctorServlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private DoctorService doctorService;
    @Mock
    private PrintWriter writer;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private DoctorDtoMapper doctorDtoMapper;
    @Mock
    private ObjectMapper objectMapper;
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctorServlet = new DoctorServlet(doctorService,doctorDtoMapper,objectMapper);
        patient = new Patient(1L, "patient");
        doctor = new Doctor(1L, "name", "doctor", List.of(patient));
    }

    @Test
    void doGet() throws IOException {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(1));
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(doctorService.findById(1L)).thenReturn(doctor);

        doctorServlet.doGet(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
        Mockito.verify(doctorDtoMapper, Mockito.times(1)).map(doctor);
    }

    @Test
    void doGetBad() throws IOException {
        Mockito.when(request.getParameter("id")).thenReturn("");
        doctorServlet.doGet(request, response);
        Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void doPost() throws IOException, SQLException {
        IncomingDoctorDto incomingDoctorDto = new IncomingDoctorDto(2L, "name1", "doctor1");
        Doctor doctor1 = new Doctor(2L, "name1", "doctor1");
        OutDoctorDto outDoctorDto = new OutDoctorDto(doctor1.getNameDoctor(), doctor1.getSpecialization());
        String reqBody = "{\"id\": \"2\" ,\"nameDoctor\":\"name1\",\"specialization\":\"doctor1\"}";
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(request.getReader()).thenReturn(bufferedReader);
        Mockito.when(bufferedReader.readLine()).thenReturn(reqBody, null);
        Mockito.when(objectMapper.readValue(reqBody, IncomingDoctorDto.class)).thenReturn(incomingDoctorDto);
        Mockito.when(doctorDtoMapper.map(incomingDoctorDto)).thenReturn(doctor1);
        Mockito.when(doctorService.save(doctor1)).thenReturn(doctor1);
        Mockito.when(doctorDtoMapper.map(doctor1)).thenReturn(outDoctorDto);
        objectMapper.writeValueAsString(outDoctorDto);

        doctorServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doPut() throws IOException, ClassNotFoundException {
        IncomingDoctorDto incomingDoctorDto = new IncomingDoctorDto(1L, "name1", "doctor1");
        Doctor doctor1 = new Doctor(1L, "name1", "doctor1");
        OutDoctorDto outDoctorDto = new OutDoctorDto(doctor1.getNameDoctor(), doctor1.getSpecialization());
        String reqBody = "{\"id\": \"1\" ,\"nameDoctor\":\"name1\",\"specialization\":\"doctor1\"}";
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(request.getReader()).thenReturn(bufferedReader);
        Mockito.when(bufferedReader.readLine()).thenReturn(reqBody, null);
        Mockito.when(objectMapper.readValue(reqBody, IncomingDoctorDto.class)).thenReturn(incomingDoctorDto);
        Mockito.when(doctorDtoMapper.map(incomingDoctorDto)).thenReturn(doctor1);
        Mockito.when(doctorService.update(doctor1)).thenReturn(doctor1);
        Mockito.when(doctorDtoMapper.map(doctor1)).thenReturn(outDoctorDto);
        objectMapper.writeValueAsString(outDoctorDto);
        doctorServlet.doPut(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);

    }

    @Test
    void doDelete() throws IOException {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(1));
        Mockito.when(doctorService.deleteById(1L)).thenReturn(true);

        doctorServlet.doDelete(request, response);

        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doDeleteBad() {
        Mockito.when(request.getParameter("id")).thenReturn("");
        doctorServlet.doDelete(request, response);
        Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }
}