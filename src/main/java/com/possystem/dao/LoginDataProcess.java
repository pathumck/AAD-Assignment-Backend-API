package com.possystem.dao;

import com.possystem.dto.LoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class LoginDataProcess implements LoginData{

    @Override
    public boolean checkCredentials(LoginDTO loginDTO, Connection connection) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = connection.prepareStatement("SELECT * FROM user WHERE name = ? AND password = ?");
            ps.setString(1, loginDTO.getName());
            ps.setString(2, loginDTO.getPassword());

            rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
