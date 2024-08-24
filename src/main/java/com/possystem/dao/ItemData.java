package com.possystem.dao;

import com.possystem.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public sealed interface ItemData permits ItemDataProcess {
    boolean saveItem(ItemDTO itemDTO, Connection connection);
    ItemDTO getItem(String id, Connection connection);
    boolean updateItem(String id, ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String id, Connection connection);
    List<ItemDTO> getAllItems(Connection connection) throws SQLException;
}
