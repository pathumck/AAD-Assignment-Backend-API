package com.possystem.bo.custom;

import com.possystem.bo.SuperBO;
import com.possystem.dto.PlaceOrderDTO;

import java.sql.Connection;

public interface OrderBO extends SuperBO {
    boolean saveOrder(PlaceOrderDTO placeOrderDTO, Connection connection);
}
