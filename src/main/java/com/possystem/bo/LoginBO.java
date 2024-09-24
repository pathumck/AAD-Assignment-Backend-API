package com.possystem.bo;

import com.possystem.dto.LoginDTO;

import java.sql.Connection;

public interface LoginBO {
    boolean checkCredentials(LoginDTO loginDTO, Connection connection);
}
