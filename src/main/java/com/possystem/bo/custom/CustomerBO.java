package com.possystem.bo.custom;

import com.possystem.bo.SuperBO;
import com.possystem.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection);
    boolean deleteCustomer(String id, Connection connection);
    CustomerDTO selectCustomer(String id, Connection connection);
    List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;
}
