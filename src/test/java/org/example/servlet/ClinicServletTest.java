package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Clinic;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.service.impl.ClinicService;
import org.example.service.impl.DoctorService;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutClinicDto;
import org.example.servlet.dto.OutDoctorDto;
import org.example.servlet.mapper.ClinicDtoMapper;
import org.example.servlet.mapper.DoctorDtoMapper;
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
class ClinicServletTest {
    @InjectMocks
    private ClinicServlet clinicServlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ClinicService clinicService;
    @Mock
    private PrintWriter writer;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private ClinicDtoMapper clinicDtoMapper;
    @Mock
    private ObjectMapper objectMapper;
    private Patient patient;
    private Doctor doctor;
    private Clinic clinic;
    @BeforeEach
    void setUp(){
       clinicServlet = new ClinicServlet(clinicDtoMapper, clinicService, objectMapper);
        patient = new Patient(1L,"patient");
        doctor = new Doctor(1L,"name","doctor", List.of(patient));
        clinic = new Clinic(1L,"clinic",List.of(doctor));
    }
    @Test
    void doGet() throws IOException, ClassNotFoundException {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(1));
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(clinicService.findById(1L)).thenReturn(clinic);

        clinicServlet.doGet(request,response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
        Mockito.verify(clinicDtoMapper, Mockito.times(1)).map(clinic);
    }
    @Test
    void doGetBad() throws IOException {
        Mockito.when(request.getParameter("id")).thenReturn("");
        clinicServlet.doGet(request,response);
        Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void doPost() throws IOException, SQLException {
        IncomingClinicDto incomingClinicDto = new IncomingClinicDto(2L, "nameClinic");
        Clinic clinic1 = new Clinic(2L, "nameClinic");
        OutClinicDto outClinicDto = new OutClinicDto(clinic1.getNameClinic());
        String reqBody = "{\"id\": \"2\" ,\"nameClinic\":\"nameClinic\"}";
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(request.getReader()).thenReturn(bufferedReader);
        Mockito.when(bufferedReader.readLine()).thenReturn(reqBody, null);
        Mockito.when(objectMapper.readValue(reqBody, IncomingClinicDto.class)).thenReturn(incomingClinicDto);
        Mockito.when(clinicDtoMapper.map(incomingClinicDto)).thenReturn(clinic1);
        Mockito.when(clinicService.save(clinic1)).thenReturn(clinic1);
        Mockito.when(clinicDtoMapper.map(clinic1)).thenReturn(outClinicDto);
        objectMapper.writeValueAsString(outClinicDto);

        clinicServlet.doPost(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doPut() throws IOException, ClassNotFoundException {
        IncomingClinicDto incomingClinicDto = new IncomingClinicDto(1L, "nameClinic");
        Clinic clinic1 = new Clinic(1L, "nameClinic");
        OutClinicDto outClinicDto = new OutClinicDto(clinic1.getNameClinic());
        String reqBody = "{\"id\": \"1\" ,\"nameClinic\":\"nameClinic\"}";
        Mockito.when(response.getWriter()).thenReturn(writer);
        Mockito.when(request.getReader()).thenReturn(bufferedReader);
        Mockito.when(bufferedReader.readLine()).thenReturn(reqBody, null);
        Mockito.when(objectMapper.readValue(reqBody, IncomingClinicDto.class)).thenReturn(incomingClinicDto);
        Mockito.when(clinicDtoMapper.map(incomingClinicDto)).thenReturn(clinic1);
        Mockito.when(clinicService.update(clinic1)).thenReturn(clinic1);
        Mockito.when(clinicDtoMapper.map(clinic1)).thenReturn(outClinicDto);
        objectMapper.writeValueAsString(outClinicDto);

        clinicServlet.doPut(request, response);

        Mockito.verify(response).setContentType("application/json");
        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doDelete() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(1));
        Mockito.when(clinicService.deleteById(1L)).thenReturn(true);

        clinicServlet.doDelete(request,response);

        Mockito.verify(response).setStatus(HttpServletResponse.SC_OK);

    }
    @Test
    void doDeleteBad() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("");
        clinicServlet.doDelete(request,response);
        Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }
}