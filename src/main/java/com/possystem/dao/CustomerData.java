package com.possystem.dao;

import com.possystem.dto.CustomerDTO;

import java.sql.Connection;

public sealed interface CustomerData permits CustomerDataProcess {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    CustomerDTO getCustomer(String id, Connection connection);
}
