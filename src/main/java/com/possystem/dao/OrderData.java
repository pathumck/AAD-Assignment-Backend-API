package com.possystem.dao;

import com.possystem.entity.Order;

import java.sql.Connection;

public sealed interface OrderData permits OrderDataProcess {
    boolean save(Order order, Connection connection);
}
