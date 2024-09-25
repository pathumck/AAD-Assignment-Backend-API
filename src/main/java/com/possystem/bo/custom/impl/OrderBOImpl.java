package com.possystem.bo.custom.impl;

import com.possystem.bo.custom.OrderBO;
import com.possystem.dao.OrderData;
import com.possystem.dao.OrderDataProcess;
import com.possystem.dto.PlaceOrderDTO;
import com.possystem.entity.Order;

import java.sql.Connection;

public class OrderBOImpl implements OrderBO {
    OrderData orderData = new OrderDataProcess();
    @Override
    public boolean saveOrder(PlaceOrderDTO placeOrderDTO, Connection connection) {
        Order order = new Order(placeOrderDTO.get_orderId(), placeOrderDTO.get_date(), placeOrderDTO.get_cusId(), placeOrderDTO.get_total());
        return orderData.save(order, connection);
    }
}
