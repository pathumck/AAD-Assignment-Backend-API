package com.possystem.controller;

import com.possystem.bo.BOFactory;
import com.possystem.bo.custom.LoginBO;
import com.possystem.bo.custom.impl.LoginBOImpl;
import com.possystem.dto.LoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login", loadOnStartup = 2)
public class LoginController extends HttpServlet {
    Connection connection;
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    LoginBO loginBO = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN);

    @Override
    public void init() throws ServletException {
        try {
            logger.info("Initializing LoginController with call init method");
            logger.trace("Error while Initializing LoginController with call init method");
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pathumpossystem");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        LoginDTO loginDTO = new LoginDTO(name, password);

        try (Writer writer = resp.getWriter()) {

            if(loginBO.checkCredentials(loginDTO, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                writer.write("Login Successful");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Invalid credentials");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Database Error");
        }
    }
}
