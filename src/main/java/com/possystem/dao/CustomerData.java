package com.possystem.dao;

import com.possystem.dto.CustomerDTO;
import com.possystem.dto.ItemDTO;

import java.sql.Connection;

public sealed interface CustomerData permits CustomerDataProcess {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    CustomerDTO getCustomer(String id, Connection connection);
    boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection);
}
