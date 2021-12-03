package com.example.chat.Servlets;

import com.example.chat.Messages.Message;
import com.example.chat.Messages.MessageList;
import com.example.chat.Utils.Utils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class AddServlet extends HttpServlet {

    private final MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);
        if (msg != null) {
            msgList.add(msg);
        } else {
            resp.getOutputStream().write("[SERVER] Error! Try again!".getBytes(StandardCharsets.UTF_8));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
