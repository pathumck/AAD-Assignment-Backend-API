package com.possystem.dao;

import com.possystem.dto.CustomerDTO;
import com.possystem.dto.ItemDTO;
import com.possystem.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public sealed interface CustomerData permits CustomerDataProcess {
    boolean save(Customer customer, Connection connection);
    CustomerDTO getCustomer(String id, Connection connection);
    boolean update(String id, Customer customer, Connection connection);
    boolean deleteCustomer(String id, Connection connection);
    List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;
}
