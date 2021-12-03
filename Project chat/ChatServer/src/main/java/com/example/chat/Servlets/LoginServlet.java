package com.example.chat.Servlets;

import com.example.chat.Users.User;
import com.example.chat.Users.UsersList;
import com.example.chat.Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/log")
public class LoginServlet extends HttpServlet {
    private final UsersList usersList = UsersList.getInstance();
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] buf = Utils.requestBodyToArray(request);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User user = gson.fromJson(bufStr, User.class);

        if (usersList.containLogin(user.getLogin())) {
            if (usersList.truePass(user.getLogin(), user.getPassword())) {
                usersList.findUserWithLogin(user.getLogin()).setOnline(true);
                response.getOutputStream().write("[SERVER] Login success".getBytes(StandardCharsets.UTF_8));
            } else { // [10] my code of login error
                response.getOutputStream().write("[SERVER] Wrong password".getBytes(StandardCharsets.UTF_8));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            }
        } else {
            response.getOutputStream().write("[SERVER] Wrong login".getBytes(StandardCharsets.UTF_8));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
