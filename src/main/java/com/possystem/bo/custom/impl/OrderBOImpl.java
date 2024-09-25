package com.possystem.bo.custom.impl;

import com.possystem.bo.custom.OrderBO;
import com.possystem.dao.OrderData;
import com.possystem.dao.OrderDataProcess;
import com.possystem.dto.PlaceOrderDTO;
import com.possystem.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    OrderData orderData = new OrderDataProcess();
    @Override
    public boolean saveOrder(PlaceOrderDTO placeOrderDTO, Connection connection) {
        Order order = new Order(placeOrderDTO.get_orderId(), placeOrderDTO.get_date(), placeOrderDTO.get_cusId(), placeOrderDTO.get_total());
        return orderData.save(order, connection);
    }

    @Override
    public String splitOrderId(Connection connection) throws SQLException {
        String lastId = orderData.generateNextId(connection);
        if (lastId == null || lastId.isEmpty() || !lastId.matches("^O\\d+$")) {
            return "O001";
        } else {
            String numericPart = lastId.substring(3);
            int numericValue = Integer.parseInt(numericPart);

            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);

            return "O00" + nextNumericPart;
        }
    }
}
