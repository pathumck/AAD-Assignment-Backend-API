package com.possystem.controller;

import com.possystem.dao.ItemData;
import com.possystem.dao.ItemDataProcess;
import com.possystem.dao.OrderDataProcess;
import com.possystem.dto.PlaceOrderDTO;
import com.possystem.entity.Order;
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

@WebServlet(urlPatterns = "/order", loadOnStartup = 2)
public class OrderController extends HttpServlet {
    Connection connection;
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Jsonb jsonb = JsonbBuilder.create();
        PlaceOrderDTO placeOrderDTO = jsonb.fromJson(req.getReader(), PlaceOrderDTO.class);

        Order order = new Order(placeOrderDTO.get_orderId(),placeOrderDTO.get_date(),placeOrderDTO.get_cusId(),placeOrderDTO.get_total());

        OrderDataProcess data = new OrderDataProcess();
        boolean isOrderSaved = data.saveOrder(order, connection);

        if (isOrderSaved) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        ItemData data1 = new ItemDataProcess();
        boolean isItemsQtyUpdated = data1.updateItemQtys(placeOrderDTO.get_cartTmList(),connection);

        if (isItemsQtyUpdated) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
