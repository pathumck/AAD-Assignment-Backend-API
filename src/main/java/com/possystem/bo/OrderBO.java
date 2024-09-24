package com.possystem.bo;

import com.possystem.dto.PlaceOrderDTO;

import java.sql.Connection;

public interface OrderBO {
    boolean saveOrder(PlaceOrderDTO placeOrderDTO, Connection connection);
}
