package com.possystem.dao;

import com.possystem.dto.tm.CartTM;
import com.possystem.entity.OrderDetail;

import java.sql.Connection;
import java.util.List;

public sealed interface OrderDetailData permits OrderDetailDataProcess {
    boolean save(List<OrderDetail> orderDetailList, Connection connection);
}
