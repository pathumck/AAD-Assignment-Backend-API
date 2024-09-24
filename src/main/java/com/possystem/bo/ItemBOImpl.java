package com.possystem.bo;

import com.possystem.dao.ItemData;
import com.possystem.dao.ItemDataProcess;
import com.possystem.dto.ItemDTO;
import com.possystem.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemData itemData = new ItemDataProcess();
    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
        Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getPrice(),itemDTO.getQty());
        return itemData.save(item, connection);
    }

    @Override
    public boolean updateItem(String id, ItemDTO itemDTO, Connection connection) {
        Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getPrice(),itemDTO.getQty());
        return itemData.update(id, item, connection);
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
