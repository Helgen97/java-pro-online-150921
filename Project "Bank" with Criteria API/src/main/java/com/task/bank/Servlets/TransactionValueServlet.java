package com.task.bank.Servlets;

import com.task.bank.Additional.TransactionXML;
import com.task.bank.DBOperation.TransactionOperation;
import com.task.bank.Entities.Transaction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet(value = "/xmlValue")
public class TransactionValueServlet extends HttpServlet {
    private final TransactionOperation transactionOperation = new TransactionOperation();
    private final TransactionXML transactionXML = new TransactionXML();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");
        double fromValue = Double.parseDouble(request.getParameter("fromValue"));
        double toValue = Double.parseDouble(request.getParameter("toValue"));
        String currency = request.getParameter("currencyName");

        List<Transaction> transactionList = transactionOperation.getTransactionsByValueRange(username, currency, fromValue, toValue);
        String fileName = username + "ValueFromTo";
        try {
            transactionXML.createXML(transactionList, fileName, getServletContext().getRealPath("/"));
            response.sendRedirect("TransactionsFiles/" + fileName + "Transactions.xml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
