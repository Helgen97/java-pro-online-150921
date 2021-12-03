package com.example.chat.Servlets;

import com.example.chat.ChatRoom.ChatRoom;
import com.example.chat.ChatRoom.RoomsList;
import com.example.chat.Users.User;
import com.example.chat.Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/add_room")
public class AddChatRoomServlet extends HttpServlet {
    private final Gson gson = new GsonBuilder().create();
    private final RoomsList roomsList = RoomsList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] buf = Utils.requestBodyToArray(request);
        String[] bufStr = new String(buf, StandardCharsets.UTF_8).split(" ");
        String roomName = bufStr[0];

        User user = gson.fromJson(bufStr[1], User.class);
        if (user != null) {
            if (!roomsList.addRoom(new ChatRoom(roomName, user))) {
                response.getOutputStream().write("[SERVER] Current room already exist".getBytes(StandardCharsets.UTF_8));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                response.getOutputStream().write("[SERVER] Room created".getBytes(StandardCharsets.UTF_8));
            }
        } else {
            response.getOutputStream().write("[SERVER] ERROR! Try again!".getBytes(StandardCharsets.UTF_8));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
