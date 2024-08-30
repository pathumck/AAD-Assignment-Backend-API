package com.possystem.dao;

import com.possystem.dto.tm.CartTM;

import java.sql.Connection;
import java.util.List;

public sealed interface OrderDetailData permits OrderDetailDataProcess {
    boolean saveOrderDetails(String orderId, List<CartTM> cartTmList, Connection connection);
}
