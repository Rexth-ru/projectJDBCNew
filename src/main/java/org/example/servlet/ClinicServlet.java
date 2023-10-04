package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Clinic;
import org.example.service.MyService;
import org.example.servlet.dto.IncomingClinicDto;
import org.example.servlet.mapper.ClinicDtoMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;

@WebServlet(name = "clinicServlet", value = "/clinic")
public class ClinicServlet extends MyServlet{
    private final MyService<Clinic> myService;
    private final ClinicDtoMapper clinicDtoMapper;
    private Gson gson;

    public ClinicServlet(MyService<Clinic> myService, ClinicDtoMapper clinicDtoMapper) {
        this.myService = myService;
        this.clinicDtoMapper = clinicDtoMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        String pathInfo = req.getPathInfo();

        Clinic clinic = clinicDtoMapper.map(new IncomingClinicDto());
        Clinic saved = null;
        try {
            saved = myService.save(clinic);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String res = gson.toJson(clinicDtoMapper.map(saved));

        PrintWriter out = resp.getWriter();

        out.print(res);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if(pathInfo == null || pathInfo.equals("/")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        if(splits.length != 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
//        Long clinicId = Long.valueOf(splits[1]);
        Long clinicId = Long.valueOf(req.getParameter("id"));
        Clinic clinic = myService.findById(clinicId);
        if(clinic==null) {

            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
if (myService.deleteById(clinicId)) {
    resp.getWriter().println(HttpServletResponse.SC_ACCEPTED);
}resp.getWriter().println(HttpServletResponse.SC_BAD_REQUEST);
//        sendAsJson(response, model);
//        return;
//    }
    }
}
