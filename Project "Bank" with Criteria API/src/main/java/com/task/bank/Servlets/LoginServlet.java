package com.task.bank.Servlets;

import com.task.bank.DBOperation.LogRegOperation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private final LogRegOperation loginOperation = new LogRegOperation();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (loginOperation.findClient(username)) {
                if(loginOperation.truePassword(username, password)){
                    setAttributes(request, username);
                    request.getRequestDispatcher("userRoom.jsp").forward(request, response);
                } else {
                    response.sendRedirect("BadGateways/loginOrPassError.jsp"); //send to wrong login/pass page
                }
            } else {
                response.sendRedirect("BadGateways/loginOrPassError.jsp"); //send to wrong login/pass page
            }
        }catch (IOException | ServletException ex){
            response.setStatus(404);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String a = request.getParameter("a");
        if(a.equals("exit")){
            HttpSession session = request.getSession();
            session.removeAttribute("login");
            session.removeAttribute("username");
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setAttributes(HttpServletRequest request, String username){
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("login", "true");
    }
}
