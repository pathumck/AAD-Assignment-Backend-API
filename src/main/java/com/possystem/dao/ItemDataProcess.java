package com.possystem.dao;

import com.possystem.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ItemDataProcess implements ItemData {
    private static final String SAVE_ITEM = "INSERT INTO item (id,name,price,qty) VALUES (?,?,?,?)";
    private static final String GET_ITEM = "SELECT * FROM item WHERE id = ?";
    private static final String UPDATE_ITEM = "UPDATE item SET name = ?,price = ?,qty = ? WHERE id = ?";
    private static final String DELETE_ITEM = "DELETE FROM item WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM item";

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

    @Override
    public ItemDTO getItem(String id, Connection connection) {
        ItemDTO itemDTO = null;

        try {
            var ps = connection.prepareStatement(GET_ITEM);
            ps.setString(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                itemDTO = new ItemDTO();
                itemDTO.setId(rs.getString("id"));
                itemDTO.setName(rs.getString("name"));
                itemDTO.setPrice(rs.getDouble("price"));
                itemDTO.setQty(rs.getInt("qty"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemDTO;
    }

    @Override
    public boolean updateItem(String id,ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEM);
            ps.setString(1, itemDTO.getName());
            ps.setDouble(2, itemDTO.getPrice());
            ps.setInt(3, itemDTO.getQty());
            ps.setString(4, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteItem(String id, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_ITEM);
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws SQLException {
        List<ItemDTO> items = new ArrayList<>();
        var ps = connection.prepareStatement(GET_ALL);
        var rs = ps.executeQuery();
        while (rs.next()) {
            ItemDTO item = new ItemDTO();
            item.setId(rs.getString("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getDouble("price"));
            item.setQty(rs.getInt("qty"));
            items.add(item);
        }
        return items;
    }
}
