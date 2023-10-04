package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Doctor;
import org.example.service.impl.DoctorService;
import org.example.servlet.dto.IncomingDoctorDto;
import org.example.servlet.dto.OutGoingDoctorDto;
import org.example.servlet.mapper.DoctorDtoMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet(name = "DoctorServlet", value = "/doctor")
public class DoctorServlet extends HttpServlet {
    private final DoctorService doctorService;

    private final DoctorDtoMapper dtoMapper;

    private final Gson gson;
//    private  final HashMap<Long, Doctor> doctors = new HashMap<>();


    public DoctorServlet(DoctorService doctorService, DoctorDtoMapper dtoMapper, Gson gson) {
        this.doctorService = doctorService;
        this.dtoMapper = dtoMapper;
        this.gson = gson;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        req.setCharacterEncoding("UTF-8");
        if (pathInfo == null || pathInfo.equals("/")) {
            Collection<Doctor> doctorCollection = doctorService.findAll();
            Collection<OutGoingDoctorDto> models = doctorCollection.stream()
                    .map(dtoMapper::map)
                    .toList();
            sendAsJson(resp, models);
            return;
        }
        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long modelId = Long.valueOf(splits[1]);
        if (doctorService.findById(modelId) == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
//        if (!doctors.containsKey(modelId)) {
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }

        sendAsJson(resp, doctorService.findById(modelId));
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String payload = buffer.toString();
            IncomingDoctorDto incomingDoctorDto = gson.fromJson(payload, IncomingDoctorDto.class);
            Doctor model = dtoMapper.map(incomingDoctorDto);
            try {
                doctorService.save(model);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dtoMapper.map(model);
//            try {
//                doctorService.save(model);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }

//            dtoMapper.map(doctors.put(Long.valueOf(req.getParameter("id")), model));

        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");

        if (splits.length != 2) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long modelId = Long.valueOf(splits[1]);

        if (doctorService.findById(modelId) == null) {

            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String payload = buffer.toString();

        Doctor model = gson.fromJson(payload, Doctor.class);
        doctorService.update(model);

//        modelId = Long.valueOf(req.getParameter("id"));
        dtoMapper.map(model);
//        doctors.put(modelId, model);

//        sendAsJson(resp, model);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");

        if (splits.length != 2) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Long modelId = Long.valueOf(splits[1]);

        doctorService.deleteById(modelId);

    }

    private void sendAsJson(
            HttpServletResponse response,
            Object obj) throws IOException {

        response.setContentType("application/json");

        String res = gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

}
