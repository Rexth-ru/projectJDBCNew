package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.repository.PatientRepository;
import org.example.repository.impl.PatientRepositoryImpl;
import org.example.service.impl.PatientService;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.IncomingPatientDTO;
import org.example.servlet.dto.OutDoctorDto;
import org.example.servlet.dto.OutPatientDto;
import org.example.servlet.mapper.PatientDtoMapper;
import org.example.servlet.mapper.PatientDtoMapperImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PatientServlet", value = "/patient/*")
public class PatientServlet extends HttpServlet {
    private PatientService patientService;
    private PatientDtoMapper patientDtoMapper;
    private ObjectMapper objectMapper;

    public PatientServlet() {
    }
    public PatientServlet(PatientService patientService, PatientDtoMapper patientDtoMapper, ObjectMapper objectMapper) {
        this.patientService = patientService;
        this.patientDtoMapper = patientDtoMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void init() throws ServletException {
        PatientRepository patientRepository = new PatientRepositoryImpl();
        patientService = new PatientService(patientRepository);
        patientDtoMapper = new PatientDtoMapperImpl();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String param = req.getParameter("id");
        if (!param.isEmpty()) {
            OutPatientDto outPatientDto = null;
            try {
                outPatientDto = patientDtoMapper.map(patientService.findById(Long.valueOf(param)));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String json = objectMapper.writeValueAsString(outPatientDto);
            resp.getWriter().write(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String reqJson = getJson(req);
        IncomingPatientDTO incomingPatientDTO = objectMapper.readValue(reqJson, IncomingPatientDTO.class);
        Patient patient = patientDtoMapper.map(incomingPatientDTO);
        try {
            OutPatientDto outPatientDto = patientDtoMapper.map(patientService.save(patient));
            String json = objectMapper.writeValueAsString(outPatientDto);
            resp.getWriter().write(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String reqJson = getJson(req);
        IncomingPatientDTO incomingPatientDTO = objectMapper.readValue(reqJson, IncomingPatientDTO.class);
        Patient patient = patientDtoMapper.map(incomingPatientDTO);
        try {
            if (patientService.update(patient) != null) {
                OutPatientDto outPatientDto = patientDtoMapper.map(patient);
                String json = objectMapper.writeValueAsString(outPatientDto);
                resp.getWriter().write(json);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String param = req.getParameter("id");
        if (!param.isEmpty()) {
            patientService.deleteById(Long.valueOf(param));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private static String getJson(HttpServletRequest req) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = req.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}

