package com.example.chat.Servlets;

import com.example.chat.Messages.MessageList;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class GetListServlet extends HttpServlet {

    private final MessageList msgList = MessageList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("from");
        String login = req.getParameter("login");
        int from;
        try {
            from = Integer.parseInt(fromStr);
            if (from < 0) from = 0;
        } catch (Exception ex) {
            resp.getOutputStream().write("[SERVER] Error! Try again!".getBytes(StandardCharsets.UTF_8));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setContentType("application/json");

        String json = msgList.toJSON(from, login);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
