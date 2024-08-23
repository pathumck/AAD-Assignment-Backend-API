package com.possystem.dao;

import com.possystem.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class ItemDataProcess implements ItemData {
    private static final String SAVE_ITEM = "INSERT INTO item (id,name,price,qty) VALUES (?,?,?,?)";

    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        try(PreparedStatement ps = connection.prepareStatement(SAVE_ITEM)) {
            ps.setString(1, itemDTO.getId());
            ps.setString(2, itemDTO.getName());
            ps.setDouble(3, itemDTO.getPrice());
            ps.setInt(4, itemDTO.getQty());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
