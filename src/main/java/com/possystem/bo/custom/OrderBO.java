package com.possystem.bo.custom;

import com.possystem.bo.SuperBO;
import com.possystem.dto.PlaceOrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    boolean saveOrder(PlaceOrderDTO placeOrderDTO, Connection connection);
    String splitOrderId(Connection connection) throws SQLException;
}
