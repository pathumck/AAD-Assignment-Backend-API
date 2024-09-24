package com.possystem.bo;

import com.possystem.dao.CustomerData;
import com.possystem.dao.CustomerDataProcess;
import com.possystem.dto.CustomerDTO;
import com.possystem.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerData customerData = new CustomerDataProcess();

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getPhone());
        return customerData.save(customer,connection);
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection) {
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getPhone());
        return customerData.update(id, customer, connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) {
        return customerData.delete(id, connection);
    }

    @Override
    public CustomerDTO selectCustomer(String id, Connection connection) {
        Customer customer = customerData.select(id, connection);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone());
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customerList = customerData.selectAll(connection);

        for (Customer customer : customerList) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone());
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
