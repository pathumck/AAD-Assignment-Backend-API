package com.possystem.bo.custom;

import com.possystem.bo.SuperBO;
import com.possystem.dto.ItemDTO;
import com.possystem.dto.tm.CartTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO, Connection connection);
    boolean updateItem(String id, ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String id, Connection connection);
    ItemDTO selectItem(String id, Connection connection);
    List<ItemDTO> selectAllItems(Connection connection) throws SQLException;
    boolean updateItemQtys(List<CartTM> cartTmList, Connection connection);
}
