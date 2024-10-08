package com.possystem.controller;

import com.possystem.bo.BOFactory;
import com.possystem.bo.custom.ItemBO;
import com.possystem.bo.custom.OrderBO;
import com.possystem.bo.custom.OrderDetailBO;
import com.possystem.bo.custom.impl.ItemBOImpl;
import com.possystem.bo.custom.impl.OrderBOImpl;
import com.possystem.bo.custom.impl.OrderDetailBOImpl;
import com.possystem.dto.PlaceOrderDTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/order", loadOnStartup = 2)
public class OrderController extends HttpServlet {
    Connection connection;
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    OrderBO orderBO = (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);
    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ITEM);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER_DETAILS);

    @Override
    public void init() throws ServletException {
        try {
            logger.info("Initializing OrderController with call init method");
            logger.trace("Error while Initializing OrderController with call init method");
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pathumpossystem");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            connection.setAutoCommit(false);

            Jsonb jsonb = JsonbBuilder.create();
            PlaceOrderDTO placeOrderDTO = jsonb.fromJson(req.getReader(), PlaceOrderDTO.class);

            boolean isOrderSaved = orderBO.saveOrder(placeOrderDTO, connection);

            if (!isOrderSaved) {
                connection.rollback();
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            boolean isItemsQtyUpdated = itemBO.updateItemQtys(placeOrderDTO.get_cartTmList(), connection);
            if (!isItemsQtyUpdated) {
                connection.rollback();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            boolean isOrderDetailsSaved = orderDetailBO.saveOrderDetails(placeOrderDTO.get_orderId(), placeOrderDTO.get_cartTmList(), connection);

            if (!isOrderDetailsSaved) {
                connection.rollback();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            connection.commit();
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    //connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");

        try {
            String newId = orderBO.splitOrderId(connection);
            if (newId != null) {
                resp.getWriter().write(newId);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Order ID not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Internal server error");
        }
    }
}
