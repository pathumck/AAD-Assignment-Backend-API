package com.possystem.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartTM {
    private String _code;
    private String _name;
    private Double _price;
    private Integer _qty;
    private Double _total;
}
