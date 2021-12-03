package com.task.bank.Servlets;

import com.task.bank.Additional.TransactionXML;
import com.task.bank.DBOperation.TransactionOperation;
import com.task.bank.Entities.Transaction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/xmlDate")
public class TransactionDateServlet extends HttpServlet {
    private final TransactionOperation transactionOperation = new TransactionOperation();
    private final TransactionXML transactionXML = new TransactionXML();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");
        String[] fromDate = request.getParameterValues("fromDate");
        String[] toDate = request.getParameterValues("toDate");

        List<Transaction> transactionList = transactionOperation.getTransactionsByDateRange(username, fromDate[0], toDate[0]);
        String fileName = username + "DateFromTo";
        try {
            transactionXML.createXML(transactionList, fileName, getServletContext().getRealPath("/"));
            response.sendRedirect("TransactionsFiles/" + fileName + "Transactions.xml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

