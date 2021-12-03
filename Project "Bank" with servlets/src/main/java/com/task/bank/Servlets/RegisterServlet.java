package com.task.bank.Servlets;

import com.task.bank.DBOperation.LogRegOperation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    private final LogRegOperation loginOperation = new LogRegOperation();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (!loginOperation.findClient(username)) {
                loginOperation.regClient(username, password);
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("BadGateways/errorClient.jsp");
            }
        }catch (IOException ex){
            response.setStatus(404);
        }
    }
}
