package com.possystem.bo.custom;

import com.possystem.bo.SuperBO;
import com.possystem.dto.tm.CartTM;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailBO extends SuperBO {
    boolean saveOrderDetails(String orderId, List<CartTM> cartTmList, Connection connection);
}
