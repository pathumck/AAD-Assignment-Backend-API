package com.possystem.bo;

import com.possystem.dto.CustomerDTO;

import java.sql.Connection;
import java.util.ArrayList;

public interface CustomerBO {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    boolean updateCustomer(CustomerDTO customerDTO);
    boolean deleteCustomer(String id);
    boolean selectCustomer(String id);
    ArrayList<CustomerDTO> getAllCustomers();
}
