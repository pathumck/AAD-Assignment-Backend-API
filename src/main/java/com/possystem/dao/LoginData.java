package com.possystem.dao;

import com.possystem.dto.LoginDTO;
import com.possystem.entity.Login;

import java.sql.Connection;

public sealed interface LoginData permits LoginDataProcess {
    boolean select(Login login, Connection connection);
}
