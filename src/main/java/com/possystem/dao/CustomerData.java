package com.possystem.dao;

import com.possystem.dto.CustomerDTO;
import com.possystem.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public sealed interface CustomerData permits CustomerDataProcess {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    CustomerDTO getCustomer(String id, Connection connection);
    boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection);
    boolean deleteCustomer(String id, Connection connection);
    List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;
}
