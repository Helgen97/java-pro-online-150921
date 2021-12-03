package com.task.bank.Servlets;

import com.task.bank.Additional.TransactionXML;
import com.task.bank.DBOperation.BasicOperations;
import com.task.bank.Entities.Transaction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet(value = "/getxml")
public class TransactionsXmlServlet extends HttpServlet {
    private final BasicOperations basicOperations = new BasicOperations();
    private final TransactionXML transactionXML = new TransactionXML();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");
        List<Transaction> transactionList = basicOperations.getTransactionsByName(username);

        try {
            transactionXML.createXML(transactionList, username, getServletContext().getRealPath("/"));
            response.sendRedirect("TransactionsFiles/" + username + "Transactions.xml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
