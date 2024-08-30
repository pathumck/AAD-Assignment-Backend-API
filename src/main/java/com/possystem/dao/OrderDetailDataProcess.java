package com.possystem.dao;

import com.possystem.dto.tm.CartTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class OrderDetailDataProcess implements OrderDetailData{
    private static final String SAVE_ORDER_DETAILS = "INSERT INTO orderdetail (orderId, itemId, quantity, price, totalPrice) VALUES (?, ?, ?, ?, ?)";
    @Override
    public boolean saveOrderDetails(String orderId, List<CartTM> cartTmList, Connection connection) {
        boolean allUpdated = true;
        PreparedStatement ps = null;
        try {
            for (CartTM cartTM : cartTmList) {
                ps = connection.prepareStatement(SAVE_ORDER_DETAILS);
                ps.setString(1, orderId);
                ps.setString(2, cartTM.get_code());
                ps.setInt(3, cartTM.get_qty());
                ps.setDouble(4, cartTM.get_price());
                ps.setDouble(5, cartTM.get_total());
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
