package com.task.bank.Servlets;

import com.task.bank.DBOperation.AccountOperation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = "/status")
public class AccountsStatusServlet extends HttpServlet {
    private final AccountOperation accountOperation = new AccountOperation();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String a = request.getParameter("a");

        if (a == null || a.equals("")) {
            String username = (String) request.getSession().getAttribute("username");
            HashMap<String, Double> accountsData = accountOperation.getAccountsData(username);
            double sum = accountOperation.getAccountSum(accountsData);
            setAttributes(request.getSession(), accountsData, sum);
            try {
                response.sendRedirect("accountsStatus.jsp");
            } catch (IOException e) {
                response.setStatus(404);
            }
        } else {
            removeAttributes(request.getSession());
            try {
                response.sendRedirect("userRoom.jsp");
            } catch (IOException e) {
                response.setStatus(404);
            }
        }
    }

    private void removeAttributes(HttpSession session) {
        session.removeAttribute("USDValue");
        session.removeAttribute("UAHValue");
        session.removeAttribute("EURValue");
        session.removeAttribute("SUM");
    }

    private void setAttributes(HttpSession session, HashMap<String, Double> accountsData, double sum) {
        double uah = 0;
        double usd = 0;
        double eur = 0;
        if (accountsData.containsKey("UAH")) {
            uah = accountsData.get("UAH");
        }
        if (accountsData.containsKey("USD")) {
            usd = accountsData.get("USD");
        }
        if (accountsData.containsKey("EUR")) {
            eur = accountsData.get("EUR");
        }

        session.setAttribute("UAHValue", uah == 0 ? "Not created or empty" : uah);
        session.setAttribute("USDValue", usd == 0 ? "Not created or empty" : usd);
        session.setAttribute("EURValue", eur == 0 ? "Not created or empty" : eur);
        session.setAttribute("SUM", sum);
    }
}
