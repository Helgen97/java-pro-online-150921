package com.task.bank.Servlets;

import com.task.bank.DBOperation.AccountOperation;
import com.task.bank.DBOperation.FundsOperation;
import com.task.bank.Entities.Transaction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/convert")
public class ConvertServlet extends HttpServlet {
    private final AccountOperation aO = new AccountOperation();
    private final FundsOperation fO = new FundsOperation();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");
        double value = Double.parseDouble(request.getParameter("value"));
        String currencyName = request.getParameter("currencyName");
        String currencyNameReceiver = request.getParameter("currencyNameReceiver");

        try {
            if (currencyName.equals(currencyNameReceiver)) response.sendRedirect("BadGateWays/convertError.jsp");
            if (aO.isAccountExist(username, currencyName) & aO.isAccountExist(username, currencyNameReceiver)) {
                if (fO.isEnoughMoney(username, currencyName, value)) {
                    Transaction transaction
                            = new Transaction(currencyName, value, username, currencyNameReceiver, "Converting funds");
                    fO.convertMoney(username, transaction);
                    response.sendRedirect("userRoom.jsp");
                } else {
                    response.sendRedirect("BadGateWays/accountError.jsp");
                }

            } else {
                response.sendRedirect("BadGateWays/accountError.jsp");
            }
        } catch (IOException ex) {
            response.setStatus(404);
        }
    }
}
