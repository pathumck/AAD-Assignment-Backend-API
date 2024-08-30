package com.possystem.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/order", loadOnStartup = 2)
public class OrderController extends HttpServlet {
}
