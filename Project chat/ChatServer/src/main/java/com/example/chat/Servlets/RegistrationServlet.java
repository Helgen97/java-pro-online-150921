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

@WebServlet(value = "/reg")
public class RegistrationServlet extends HttpServlet {
    private final UsersList usersList = UsersList.getInstance();
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] buf = Utils.requestBodyToArray(request);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User user = gson.fromJson(bufStr, User.class);

        if (user != null) {
            if (!usersList.containLogin(user.getLogin())) {
                usersList.add(user); //[2] my code of register success
                response.getOutputStream().write("[SERVER] User successfully registered!".getBytes(StandardCharsets.UTF_8));
            } else { //[20] my code of register error
                response.getOutputStream().write("[SERVER] User already registered!".getBytes(StandardCharsets.UTF_8));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } else {
            response.getOutputStream().write("[SERVER] Error! Try again!".getBytes(StandardCharsets.UTF_8));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }


    }
}
