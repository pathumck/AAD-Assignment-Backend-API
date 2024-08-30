package com.possystem.dto;

import com.possystem.dto.tm.CartTM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderDTO {
    private String _orderId;
    private String _cusId;
    private String _total;
    private String _date;
    private List<CartTM> _cartTmList;
}
