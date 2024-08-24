package com.possystem.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/login", loadOnStartup = 2)
public class LoginController extends HttpServlet {
}
