package com.possystem.bo;

import com.possystem.dto.tm.CartTM;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailBO {
    boolean saveOrderDetails(String orderId, List<CartTM> cartTmList, Connection connection);
}
