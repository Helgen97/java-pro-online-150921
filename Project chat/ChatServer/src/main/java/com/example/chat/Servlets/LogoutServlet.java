package com.example.chat.Servlets;

import com.example.chat.Users.UsersList;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {
    private final UsersList usersList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");

        if (usersList.containLogin(login)) {
            usersList.findUserWithLogin(login).setOnline(false);
        }
    }
}
