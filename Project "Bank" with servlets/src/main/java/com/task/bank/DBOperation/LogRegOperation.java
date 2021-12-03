package com.task.bank.DBOperation;

import com.task.bank.Entities.Account;
import com.task.bank.Entities.Client;

public class LogRegOperation extends BasicOperations {

    public void regClient(String username, String password) {
        Client client = new Client(username, password);
        Account account = new Account("UAH");
        client.addAccount(account);
        addToDB(client);
    }

    public boolean findClient(String username) {
        Client client = getClientByName(username);
        return client != null;
    }

    public boolean truePassword(String username, String password) {
        Client client = getClientByName(username);
        return client.getPassword().equals(password);
    }
}
