package com.possystem.dao;

import com.possystem.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public sealed interface OrderData permits OrderDataProcess {
    boolean save(Order order, Connection connection);
    String generateNextId(Connection connection) throws SQLException;
}
