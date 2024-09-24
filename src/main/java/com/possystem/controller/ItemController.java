package com.possystem.controller;

import com.possystem.bo.ItemBO;
import com.possystem.bo.ItemBOImpl;
import com.possystem.dao.CustomerDataProcess;
import com.possystem.dao.ItemDataProcess;
import com.possystem.dto.ItemDTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item",loadOnStartup = 2)
public class ItemController extends HttpServlet {
    Connection connection;
    ItemBO itemBO = new ItemBOImpl();

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
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        if (itemBO.saveItem(itemDTO,connection)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Writer writer = resp.getWriter();
        var data = new ItemDataProcess();
        Jsonb jsonb = JsonbBuilder.create();
        resp.setContentType("application/json");

        try {
            if (id != null) {
                var item = data.getItem(id, connection);
                jsonb.toJson(item, writer);
            } else {
                var items = data.getAllItems(connection);
                jsonb.toJson(items, writer);
            }
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json")||req.getContentType()==null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try(var writer = resp.getWriter()) {
            var id = req.getParameter("id");
            Jsonb jsonb = JsonbBuilder.create();
            var item = jsonb.fromJson(req.getReader(), ItemDTO.class);
            if (itemBO.updateItem(id,item,connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Update failed");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (JsonbException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        try(var writer = resp.getWriter()) {
            if(itemBO.deleteItem(id,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Delete failed");
            }
        }catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
