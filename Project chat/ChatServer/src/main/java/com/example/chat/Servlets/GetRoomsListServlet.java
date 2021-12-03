package com.example.chat.Servlets;


import com.example.chat.ChatRoom.RoomsList;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/get_rooms")
public class GetRoomsListServlet extends HttpServlet {
    private RoomsList roomsList = RoomsList.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String roomList = roomsList.toString();
        if (roomList != null) {
            OutputStream os = response.getOutputStream();
            byte[] buf = roomList.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
