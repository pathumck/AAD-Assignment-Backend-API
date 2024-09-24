package com.possystem.bo;

import com.possystem.dao.ItemData;
import com.possystem.dao.ItemDataProcess;
import com.possystem.dto.ItemDTO;
import com.possystem.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return itemData.delete(id, connection);
    }

    @Override
    public ItemDTO selectItem(String id, Connection connection) {
        Item item = itemData.select(id, connection);
        return new ItemDTO(item.getId(), item.getName(), item.getPrice(), item.getQty());
    }

    @Override
    public List<ItemDTO> selectAllItems(Connection connection) throws SQLException {
        List<Item> itemList = itemData.selectAll(connection);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : itemList) {
            ItemDTO itemDTO = new ItemDTO(item.getId(), item.getName(), item.getPrice(), item.getQty());
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }
}
