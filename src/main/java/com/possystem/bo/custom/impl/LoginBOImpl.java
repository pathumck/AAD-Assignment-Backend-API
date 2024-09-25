package com.possystem.bo.custom.impl;

import com.possystem.bo.custom.LoginBO;
import com.possystem.dao.LoginData;
import com.possystem.dao.LoginDataProcess;
import com.possystem.dto.LoginDTO;
import com.possystem.entity.Login;

import java.sql.Connection;

public class LoginBOImpl implements LoginBO {
    LoginData loginData = new LoginDataProcess();

    @Override
    public boolean checkCredentials(LoginDTO loginDTO, Connection connection) {
        Login login = new Login(loginDTO.getName(),loginDTO.getPassword());
        return loginData.select(login, connection);
    }
}
