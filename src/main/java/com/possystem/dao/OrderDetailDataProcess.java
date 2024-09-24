package com.possystem.dao;

import com.possystem.dto.tm.CartTM;
import com.possystem.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class OrderDetailDataProcess implements OrderDetailData{
    private static final String SAVE_ORDER_DETAILS = "INSERT INTO orderdetail (orderId, itemId, quantity, price, totalPrice) VALUES (?, ?, ?, ?, ?)";
    @Override
    public boolean save(List<OrderDetail> orderDetailList, Connection connection) {
        boolean allUpdated = true;
        PreparedStatement ps = null;
        try {
            for (OrderDetail orderDetail : orderDetailList) {
                ps = connection.prepareStatement(SAVE_ORDER_DETAILS);
                ps.setString(1, orderDetail.getOrderId());
                ps.setString(2, orderDetail.getItemId());
                ps.setInt(3, orderDetail.getQty());
                ps.setDouble(4, orderDetail.getPrice());
                ps.setDouble(5, orderDetail.getTotal());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    allUpdated = false;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            allUpdated = false;
        }
        return allUpdated;
    }
}
