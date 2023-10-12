package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Clinic;
import org.example.service.impl.ClinicService;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.dto.OutClinicDto;
import org.example.servlet.mapper.ClinicDtoMapper;
import org.example.servlet.mapper.ClinicDtoMapperImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "clinicServlet", value = "/clinic")
public class ClinicServlet extends HttpServlet {
    private ClinicDtoMapper clinicDtoMapper;
    private ClinicService clinicService;
    private ObjectMapper objectMapper;

    public ClinicServlet() {
    }

    public ClinicServlet(ClinicDtoMapper clinicDtoMapper, ClinicService clinicService, ObjectMapper objectMapper) {
        this.clinicDtoMapper = clinicDtoMapper;
        this.clinicService = clinicService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void init() throws ServletException {
        clinicService = new ClinicService();
        clinicDtoMapper = new ClinicDtoMapperImpl();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String param = req.getParameter("id");
        if (!param.isEmpty()) {
            OutClinicDto outClinicDto = null;
            try {
                outClinicDto = clinicDtoMapper.map(clinicService.findById(Long.valueOf(param)));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String json = objectMapper.writeValueAsString(outClinicDto);
            resp.getWriter().write(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String reqJson = getJson(req);
        IncomingClinicDto incomingClinicDto = objectMapper.readValue(reqJson, IncomingClinicDto.class);
        Clinic clinic = clinicDtoMapper.map(incomingClinicDto);
        try {
            OutClinicDto outClinicDto = clinicDtoMapper.map(clinicService.save(clinic));
            String json = objectMapper.writeValueAsString(outClinicDto);
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
        IncomingClinicDto incomingClinicDto = objectMapper.readValue(reqJson, IncomingClinicDto.class);
        Clinic clinic = clinicDtoMapper.map(incomingClinicDto);
        try {
            if (clinicService.update(clinic) != null) {
                OutClinicDto outClinicDto = clinicDtoMapper.map(clinic);
                String json = objectMapper.writeValueAsString(outClinicDto);
                resp.getWriter().write(json);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        if (!param.isEmpty()) {
            clinicService.deleteById(Long.valueOf(param));
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