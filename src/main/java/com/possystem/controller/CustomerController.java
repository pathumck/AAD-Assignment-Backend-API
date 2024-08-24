package com.possystem.controller;

import com.possystem.dao.CustomerDataProcess;
import com.possystem.dao.ItemDataProcess;
import com.possystem.dto.CustomerDTO;
import com.possystem.dto.ItemDTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer", loadOnStartup = 2)
public class CustomerController extends HttpServlet {
    Connection connection = null;

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pathumpossystem");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json")||req.getContentType()==null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        var saveData = new CustomerDataProcess();
        if (saveData.saveCustomer(customerDTO,connection)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
