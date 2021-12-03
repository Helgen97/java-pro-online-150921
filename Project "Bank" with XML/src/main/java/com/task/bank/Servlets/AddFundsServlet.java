package com.task.bank.Servlets;

import com.task.bank.DBOperation.AccountOperation;
import com.task.bank.DBOperation.FundsOperation;
import com.task.bank.Entities.Transaction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/addFunds")
public class AddFundsServlet extends HttpServlet {
    private final AccountOperation accountOperation = new AccountOperation();
    private final FundsOperation addFundsOperation = new FundsOperation();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");
        double value = Double.parseDouble(request.getParameter("value"));
        String currencyName = request.getParameter("currencyName");

        try {
            if (accountOperation.isAccountExist(username, currencyName)) {
                Transaction transaction = new Transaction(currencyName, value, username, currencyName , "Add funds");
                addFundsOperation.addFundsToClient(username, transaction);
                response.sendRedirect("userRoom.jsp");
            } else {
                response.sendRedirect("BadGateways/accountError.jsp");
            }
        } catch (IOException ex) {
            response.setStatus(404);
        }
    }
}
