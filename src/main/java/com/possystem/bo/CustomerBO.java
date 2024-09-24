package com.possystem.bo;

import com.possystem.dto.CustomerDTO;

import java.sql.Connection;
import java.util.ArrayList;

public interface CustomerBO {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection);
    boolean deleteCustomer(String id, Connection connection);
    boolean selectCustomer(String id);
    ArrayList<CustomerDTO> getAllCustomers();
}
