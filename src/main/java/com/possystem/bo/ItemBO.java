package com.possystem.bo;

import com.possystem.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO {
    boolean saveItem(ItemDTO itemDTO, Connection connection);
    boolean updateItem(String id, ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String id, Connection connection);
    ItemDTO selectItem(String id, Connection connection);
    List<ItemDTO> getAllItems(Connection connection) throws SQLException;
}
