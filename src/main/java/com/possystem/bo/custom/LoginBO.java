package com.possystem.bo.custom;

import com.possystem.bo.SuperBO;
import com.possystem.dto.LoginDTO;

import java.sql.Connection;

public interface LoginBO extends SuperBO {
    boolean checkCredentials(LoginDTO loginDTO, Connection connection);
}
