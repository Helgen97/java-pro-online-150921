package com.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/login")
public class Login extends HttpServlet {
    private final Map<String, String> loginAndPass = new HashMap<>();

    @Override
    public void init() {
        loginAndPass.put("admin", "admin");
        loginAndPass.put("user", "qwerty");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String register = request.getParameter("register");
        if (register == null || register.equals("")) {
            String login = request.getParameter("login");
            String pass = request.getParameter("pass");
            HttpSession session = request.getSession();
            if (pass.equals(loginAndPass.get(login))) {
                session.setAttribute("login", login);
                session.setAttribute("logIn", "ok");
                response.sendRedirect("granted.jsp");
            } else {
                response.sendRedirect("denied.jsp");
            }
        } else if (register.equals("new")) {
            String login = request.getParameter("newLogin");
            String password = request.getParameter("newPassword");
            loginAndPass.put(login, password);
            response.sendRedirect("success.jsp");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String a = request.getParameter("a");
        if (a.equals("exit")) {
            removeAttribute(request);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect("statistic.jsp");
        }
    }

    private void removeAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.removeAttribute("logIn");
        session.removeAttribute("login");

        session.removeAttribute("userName");
        session.removeAttribute("userSurname");
        session.removeAttribute("userAge");

        session.removeAttribute("visitsOftenEvery");
        session.removeAttribute("visitsOftenFew");
        session.removeAttribute("visitsOftenOnce");
        session.removeAttribute("visitsOftenNever");

        session.removeAttribute("staff1");
        session.removeAttribute("staff2");
        session.removeAttribute("staff3");
        session.removeAttribute("staff4");
        session.removeAttribute("staff5");

        session.removeAttribute("cafe1");
        session.removeAttribute("cafe2");
        session.removeAttribute("cafe3");
        session.removeAttribute("cafe4");
        session.removeAttribute("cafe5");

        session.removeAttribute("cafeRecomYes");
        session.removeAttribute("cafeRecomNo");

        session.removeAttribute("yes");
        session.removeAttribute("no");
    }
}
