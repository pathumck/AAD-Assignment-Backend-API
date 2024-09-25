package com.possystem.bo.custom.impl;

import com.possystem.bo.custom.OrderDetailBO;
import com.possystem.dao.OrderDetailData;
import com.possystem.dao.OrderDetailDataProcess;
import com.possystem.dto.tm.CartTM;
import com.possystem.entity.OrderDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailData orderDetailData = new OrderDetailDataProcess();
    @Override
    public boolean saveOrderDetails(String orderId, List<CartTM> cartTmList, Connection connection) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartTM cartTM : cartTmList) {
            OrderDetail orderDetail = new OrderDetail(orderId, cartTM.get_code(), cartTM.get_qty(), cartTM.get_price(), cartTM.get_total());
            orderDetailList.add(orderDetail);
        }
        return orderDetailData.save(orderDetailList, connection);
    }
}
