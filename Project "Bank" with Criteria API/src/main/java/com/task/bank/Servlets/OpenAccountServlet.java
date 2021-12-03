package com.task.bank.Servlets;

import com.task.bank.DBOperation.AccountOperation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/newAcc")
public class OpenAccountServlet extends HttpServlet {
    private final AccountOperation accountOperation = new AccountOperation();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String currencyName = request.getParameter("currencyName");

        try {
            if(!accountOperation.isAccountExist(username, currencyName)){
                accountOperation.addAccount(username, currencyName);
                response.sendRedirect("userRoom.jsp");
            } else {
                response.sendRedirect("BadGateways/accountErrorExist.jsp");
            }
        }catch (IOException ex){
            response.setStatus(404);
        }
    }
}
