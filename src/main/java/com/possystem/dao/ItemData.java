package com.possystem.dao;

import com.possystem.dto.ItemDTO;

import java.sql.Connection;

public sealed interface ItemData permits ItemDataProcess {
    boolean saveItem(ItemDTO itemDTO, Connection connection);
    ItemDTO getItem(String id, Connection connection);
}
