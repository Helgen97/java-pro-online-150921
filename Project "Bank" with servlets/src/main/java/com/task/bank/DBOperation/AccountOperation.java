package com.task.bank.DBOperation;

import com.task.bank.Entities.Account;
import com.task.bank.Entities.Client;
import com.task.bank.Entities.ExchangeRate;

import java.util.HashMap;
import java.util.List;

public class AccountOperation extends BasicOperations {

    public boolean isAccountExist(String username, String currencyName) {
        Client client = getClientByName(username);
        List<Account> accounts = client.getAccounts();
        for (Account account : accounts) {
            if (account.getCurrencyName().equals(currencyName)) return true;
        }
        return false;
    }

    public void addAccount(String username, String currencyName) {
        Client client = getClientByName(username);
        Account account = new Account(currencyName);

        getEm().getTransaction().begin();
        try {
            client.addAccount(account);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            getEm().getTransaction().rollback();
        }
    }

    public List<Account> getAccountsList(String username) {
        Client client = getClientByName(username);
        return client.getAccounts();
    }

    public HashMap<String, Double> getAccountsData(String username) {
        HashMap<String, Double> accountsData = new HashMap<>();

        List<Account> accounts = getAccountsList(username);
        for (Account account : accounts) {
            switch (account.getCurrencyName()) {
                case "UAH":
                    accountsData.put("UAH", account.getValue());
                    break;
                case "USD":
                    accountsData.put("USD", account.getValue());
                    break;
                case "EUR":
                    accountsData.put("EUR", account.getValue());
                    break;
            }
        }
        return accountsData;
    }

    public double getAccountSum(HashMap<String, Double> accountsData) {
        double sumInUAH = 0;
        ExchangeRate usd = getExchangeRateByName("USD");
        ExchangeRate eur = getExchangeRateByName("EUR");

        if (accountsData.containsKey("UAH")) {
            sumInUAH += accountsData.get("UAH");
        }
        if (accountsData.containsKey("USD")) {
            sumInUAH += accountsData.get("USD") * usd.getValue();
        }
        if (accountsData.containsKey("EUR")) {
            sumInUAH += accountsData.get("EUR") * eur.getValue();
        }

        return sumInUAH;
    }

}
