package com.example.chat.Servlets;

import com.example.chat.Users.UsersList;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/get_users")
public class GetUserListServlet extends HttpServlet {
    private final UsersList usersList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String userList = usersList.toString();
        if (userList != null) {
            OutputStream os = response.getOutputStream();
            byte[] buf = userList.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
