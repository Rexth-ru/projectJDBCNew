package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Doctor;
import org.example.repository.DoctorRepository;
import org.example.repository.impl.DoctorRepositoryImpl;
import org.example.service.impl.DoctorService;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutDoctorDto;
import org.example.servlet.mapper.DoctorDtoMapper;
import org.example.servlet.mapper.DoctorDtoMapperImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DoctorServlet", value = "/doctor")
public class DoctorServlet extends HttpServlet {
    private DoctorService doctorService;
    private DoctorDtoMapper doctorDtoMapper;
    private ObjectMapper objectMapper;

    public DoctorServlet() {
    }
    public DoctorServlet(DoctorService doctorService, DoctorDtoMapper doctorDtoMapper, ObjectMapper objectMapper) {
        this.doctorService = doctorService;
        this.doctorDtoMapper = doctorDtoMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void init() throws ServletException {
        DoctorRepository doctorRepository = new DoctorRepositoryImpl();
        doctorService = new DoctorService(doctorRepository);
        doctorDtoMapper = new DoctorDtoMapperImpl();
        objectMapper = new ObjectMapper();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String param = req.getParameter("id");
        if (!param.isEmpty()) {
            OutDoctorDto outDoctorDto = doctorDtoMapper.map(doctorService.findById(Long.valueOf(param)));
            String json = objectMapper.writeValueAsString(outDoctorDto);
            resp.getWriter().write(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String reqJson = getJson(req);
        IncomingDoctorDto incomingDoctorDto = objectMapper.readValue(reqJson, IncomingDoctorDto.class);
        Doctor doctor = doctorDtoMapper.map(incomingDoctorDto);
        try {
            OutDoctorDto outDoctorDto = doctorDtoMapper.map(doctorService.save(doctor));
            String json = objectMapper.writeValueAsString(outDoctorDto);
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
        IncomingDoctorDto incomingDoctorDto = objectMapper.readValue(reqJson, IncomingDoctorDto.class);
        Doctor doctor = doctorDtoMapper.map(incomingDoctorDto);
        try {
            if (doctorService.update(doctor) != null) {
                OutDoctorDto outDoctorDto = doctorDtoMapper.map(doctor);
                String json = objectMapper.writeValueAsString(outDoctorDto);
                resp.getWriter().write(json);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        String param = req.getParameter("id");
        if (!param.isEmpty()) {
            doctorService.deleteById(Long.valueOf(param));
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