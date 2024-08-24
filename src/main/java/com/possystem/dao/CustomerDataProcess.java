package com.possystem.dao;

import com.possystem.dto.CustomerDTO;
import com.possystem.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class CustomerDataProcess implements CustomerData {

    private static final String SAVE_CUSTOMER = "INSERT INTO customer (id,name,address,phone) VALUES (?,?,?,?)";
    private static final String GET_CUSTOMER = "SELECT * FROM customer WHERE id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE customer SET name = ?,address = ?,phone = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE id = ?";

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        try(PreparedStatement ps = connection.prepareStatement(SAVE_CUSTOMER)) {
            ps.setString(1, customerDTO.getId());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getAddress());
            ps.setString(4, customerDTO.getPhone());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) {
        CustomerDTO customerDTO = null;

        try {
            var ps = connection.prepareStatement(GET_CUSTOMER);
            ps.setString(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                customerDTO = new CustomerDTO();
                customerDTO.setId(rs.getString("id"));
                customerDTO.setName(rs.getString("name"));
                customerDTO.setAddress(rs.getString("address"));
                customerDTO.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDTO;
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_CUSTOMER);
            ps.setString(1, customerDTO.getName());
            ps.setString(2, customerDTO.getAddress());
            ps.setString(3, customerDTO.getPhone());
            ps.setString(4, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_CUSTOMER);
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
