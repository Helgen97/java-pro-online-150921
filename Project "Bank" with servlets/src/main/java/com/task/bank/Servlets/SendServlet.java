package com.task.bank.Servlets;

import com.task.bank.DBOperation.AccountOperation;
import com.task.bank.DBOperation.FundsOperation;
import com.task.bank.Entities.Transaction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/send")
public class SendServlet extends HttpServlet {
    private final AccountOperation accountOperation = new AccountOperation();
    private final FundsOperation fundsOperation = new FundsOperation();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");
        double value = Double.parseDouble(request.getParameter("value"));
        String currencyName = request.getParameter("currencyName");
        String receiver = request.getParameter("receiver");
        String currencyNameReceiver = request.getParameter("currencyNameReceiver");
        String comment = request.getParameter("comment");

        try {
            if (accountOperation.isClientExist(receiver) & accountOperation.isAccountExist(receiver, currencyNameReceiver)) {
                if (accountOperation.isAccountExist(username, currencyName) & fundsOperation.isEnoughMoney(username, currencyName, value)) {
                    Transaction transaction
                            = new Transaction(currencyName, value, receiver, currencyNameReceiver, comment);
                    fundsOperation.sendMoney(username, transaction);
                    response.sendRedirect("userRoom.jsp");
                } else {
                    response.sendRedirect("BadGateways/accountError.jsp");
                }
            } else {
                response.sendRedirect("BadGateways/receiverError.jsp");
            }
        } catch (IOException ex) {
            response.setStatus(404);
        }
    }
}
