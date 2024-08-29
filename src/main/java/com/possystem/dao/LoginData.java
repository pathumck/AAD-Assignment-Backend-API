package com.possystem.dao;

import com.possystem.dto.LoginDTO;

import java.sql.Connection;

public sealed interface LoginData permits LoginDataProcess {
    boolean checkCredentials(LoginDTO loginDTO, Connection connection);
}
