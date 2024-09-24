package com.possystem.dao;

import com.possystem.dto.CustomerDTO;
import com.possystem.dto.ItemDTO;
import com.possystem.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public sealed interface CustomerData permits CustomerDataProcess {
    boolean save(Customer customer, Connection connection);
    Customer select(String id, Connection connection);
    boolean update(String id, Customer customer, Connection connection);
    boolean delete(String id, Connection connection);
    List<Customer> selectAll(Connection connection) throws SQLException;
}
