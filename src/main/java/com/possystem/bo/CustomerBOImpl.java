package com.possystem.bo;

import com.possystem.dao.CustomerData;
import com.possystem.dao.CustomerDataProcess;
import com.possystem.dto.CustomerDTO;
import com.possystem.entity.Customer;

import java.sql.Connection;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerData customerData = new CustomerDataProcess();

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getPhone());
        return customerData.save(customer,connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public boolean selectCustomer(String id) {
        return false;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {
        return null;
    }
}
