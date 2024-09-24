package com.possystem.bo;

import com.possystem.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        return false;
    }

    @Override
    public boolean updateItem(String id, ItemDTO itemDTO, Connection connection) {
        return false;
    }

    @Override
    public boolean deleteItem(String id, Connection connection) {
        return false;
    }

    @Override
    public ItemDTO selectItem(String id, Connection connection) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws SQLException {
        return null;
    }
}
