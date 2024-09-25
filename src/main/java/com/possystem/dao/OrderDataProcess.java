package com.possystem.dao;

import com.possystem.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class OrderDataProcess implements OrderData {
    private static final String SAVE_ORDER = "INSERT INTO Orders (id, date, customerId, total) VALUES (?, ?, ?, ?)";
    private static final String GET_LAST_ID = "SELECT id FROM Orders WHERE id LIKE 'O00%' ORDER BY CAST(SUBSTRING(id, 4) AS UNSIGNED) DESC LIMIT 1";

    @Override
    public boolean save(Order order, Connection connection) {
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

    @Override
    public String generateNextId(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_LAST_ID);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
