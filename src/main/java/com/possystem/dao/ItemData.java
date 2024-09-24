package com.possystem.dao;

import com.possystem.dto.ItemDTO;
import com.possystem.dto.tm.CartTM;
import com.possystem.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public sealed interface ItemData permits ItemDataProcess {
    boolean save(Item item, Connection connection);
    ItemDTO getItem(String id, Connection connection);
    boolean update(String id, Item item, Connection connection);
    boolean delete(String id, Connection connection);
    List<ItemDTO> getAllItems(Connection connection) throws SQLException;
    boolean updateItemQtys(List<CartTM> cartTmList, Connection connection);
}
