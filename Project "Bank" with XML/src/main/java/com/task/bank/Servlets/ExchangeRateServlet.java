package com.task.bank.Servlets;

import com.task.bank.DBOperation.ExchangeRateOperation;
import com.task.bank.Entities.ExchangeRate;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.util.List;

@WebListener
public class ExchangeRateServlet implements ServletContextListener {
    private final ExchangeRateOperation operation = new ExchangeRateOperation();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        try {
            ExchangeRate[] exchangeRates = operation.getExchangesRate();
            operation.addExchangeRatesToDB(exchangeRates);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        List<ExchangeRate> exchangeRateList = operation.getExchangeRates();
        operation.deleteRates(exchangeRateList);
    }
}
