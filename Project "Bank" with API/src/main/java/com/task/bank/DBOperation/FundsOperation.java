package com.task.bank.DBOperation;

import com.task.bank.Entities.Account;
import com.task.bank.Entities.Client;
import com.task.bank.Entities.ExchangeRate;
import com.task.bank.Entities.Transaction;

public class FundsOperation extends BasicOperations {

    public void addFundsToClient(String username, Transaction transaction) {
        Client client = getClientByName(username);

        getEm().getTransaction().begin();
        try {
            client.addTransaction(transaction);

            for (Account account : client.getAccounts()) {
                if (account.getCurrencyName().equals(transaction.getCurrencyName())) {
                    account.addFunds(transaction.getValue());
                }
            }

            getEm().getTransaction().commit();
        } catch (Exception ex) {
            getEm().getTransaction().rollback();
        }
    }

    public void convertMoney(String username, Transaction transaction) {
        Client client = getClientByName(username);
        getEm().getTransaction().begin();
        try {
            client.addTransaction(transaction);

            if (transaction.getCurrencyName().equals("UAH")) {
                uahCurrency(client, transaction);
            } else if (transaction.getReceiveCurrency().equals("UAH")) {
                receiveCurrencyUah(client, transaction);
            } else {
                anotherCurrency(client, transaction);
            }
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            getEm().getTransaction().rollback();
        }

    }

    private void uahCurrency(Client client, Transaction transaction) {
        ExchangeRate exchangeRate = getExchangeRateByName(transaction.getReceiveCurrency());

        double newValue = transaction.getValue() / exchangeRate.getSale();

        for (Account account : client.getAccounts()) {
            if (account.getCurrencyName().equals(transaction.getCurrencyName())) {
                account.decreaseFunds(transaction.getValue());
            } else if (account.getCurrencyName().equals(transaction.getReceiveCurrency())) {
                account.addFunds(newValue);
            }
        }
    }

    private void anotherCurrency(Client client, Transaction transaction) {
        ExchangeRate currencySend = getExchangeRateByName(transaction.getCurrencyName());
        ExchangeRate currencyReceive = getExchangeRateByName(transaction.getReceiveCurrency());

        double changeToUah = transaction.getValue() * currencySend.getBuy();
        double receiveValue = changeToUah / currencyReceive.getSale();

        for (Account account : client.getAccounts()) {
            if (account.getCurrencyName().equals(transaction.getCurrencyName())) {
                account.decreaseFunds(transaction.getValue());
            } else if (account.getCurrencyName().equals(transaction.getReceiveCurrency())) {
                account.addFunds(receiveValue);
            }
        }
    }

    private void receiveCurrencyUah(Client client, Transaction transaction) {
        ExchangeRate sendCurrency = getExchangeRateByName(transaction.getCurrencyName());

        double receiveValue = transaction.getValue() * sendCurrency.getBuy();

        for (Account account : client.getAccounts()) {
            if (account.getCurrencyName().equals(transaction.getCurrencyName())) {
                account.decreaseFunds(transaction.getValue());
            } else if (account.getCurrencyName().equals(transaction.getReceiveCurrency())) {
                account.addFunds(receiveValue);
            }
        }
    }

    public void sendMoney(String username, Transaction transaction) {
        Client clientSender = getClientByName(username);
        Client clientReceiver = getClientByName(transaction.getReceiverName());

        getEm().getTransaction().begin();

        try {
            clientSender.addTransaction(transaction);
            if (transaction.getCurrencyName().equals(transaction.getReceiveCurrency())) {
                setMoneySameCurrency(clientSender, clientReceiver, transaction);
            } else {
                setMoneyDifferentCurrency(clientSender, clientReceiver, transaction);
            }
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            getEm().getTransaction().rollback();
        }

    }

    private void setMoneySameCurrency(Client clientSender, Client clientReceiver, Transaction transaction) {
        for (Account account : clientSender.getAccounts()) {
            if (account.getCurrencyName().equals(transaction.getCurrencyName())) {
                account.decreaseFunds(transaction.getValue());
            }
        }
        for (Account account : clientReceiver.getAccounts()) {
            if (account.getCurrencyName().equals(transaction.getReceiveCurrency())) {
                account.addFunds(transaction.getValue());
            }
        }
    }

    private void setMoneyDifferentCurrency(Client clientSender, Client clientReceiver, Transaction transaction) {
        for (Account account : clientSender.getAccounts()) {
            if (account.getCurrencyName().equals(transaction.getCurrencyName())) {
                account.decreaseFunds(transaction.getValue());
            }
        }
        ExchangeRate exchangeRate = getExchangeRateByName(transaction.getReceiveCurrency());
        double newValue = transaction.getValue() / exchangeRate.getSale();
        for (Account account : clientReceiver.getAccounts()) {
            if (account.getCurrencyName().equals(transaction.getReceiveCurrency())) {
                account.addFunds(newValue);
            }
        }
    }

    public boolean isEnoughMoney(String username, String currencyName, double value) {
        Client client = getClientByName(username);
        for (Account account : client.getAccounts()) {
            if (account.getCurrencyName().equals(currencyName)) {
                if (account.getValue() >= value) return true;
                break;
            }
        }
        return false;
    }


}
