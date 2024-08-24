package com.possystem.dao;

import com.possystem.dto.ItemDTO;

import java.sql.Connection;

public sealed interface ItemData permits ItemDataProcess {
    boolean saveItem(ItemDTO itemDTO, Connection connection);
    ItemDTO getItem(String id, Connection connection);
    boolean updateItem(String id, ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String id, Connection connection);
}
