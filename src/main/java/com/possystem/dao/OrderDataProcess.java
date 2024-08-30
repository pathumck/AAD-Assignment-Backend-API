package com.possystem.dao;

import com.possystem.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class OrderDataProcess implements OrderData {
    private static final String SAVE_ORDER = "INSERT INTO Orders (id, date, customerId, total) VALUES (?, ?, ?, ?)";
    @Override
    public boolean saveOrder(Order order, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_ORDER);
            ps.setString(1, order.getId());
            ps.setString(2, order.getDate());
            ps.setString(3, order.getCustomerId());
            ps.setString(4, order.getTotal());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
