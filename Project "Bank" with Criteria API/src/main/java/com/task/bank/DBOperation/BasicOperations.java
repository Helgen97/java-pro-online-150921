package com.task.bank.DBOperation;

import com.task.bank.Entities.Account;
import com.task.bank.Entities.Client;
import com.task.bank.Entities.ExchangeRate;
import com.task.bank.Entities.Transaction;
import jakarta.persistence.*;

import java.util.List;

public class BasicOperations {
    private final SessionDB sessionDB = SessionDB.getInstance();


    public <T> void addToDB(T... t) {
        EntityTransaction transaction = getEm().getTransaction();
        transaction.begin();
        try {
            for (T o : t) {
                sessionDB.getEm().persist(o);
            }
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    public boolean isClientExist(String username) {
        Client client = getClientByName(username);
        return client != null;
    }

    public Client getClientByName(String username) {
        try {
            TypedQuery<Client> query = getEm().createQuery(
                    "SELECT c FROM Client c WHERE c.nickname = :name", Client.class
            );
            query.setParameter("name", username);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }

    public ExchangeRate getExchangeRateByName(String currencyName) {
        try {
            TypedQuery<ExchangeRate> query = getEm().createQuery(
                    "SELECT ex FROM ExchangeRate ex WHERE ex.ccy = :name", ExchangeRate.class
            );
            query.setParameter("name", currencyName);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }

    public List<ExchangeRate> getExchangeRates() {
        TypedQuery<ExchangeRate> query = getEm().createQuery("select ex from ExchangeRate ex", ExchangeRate.class);
        return query.getResultList();
    }

    public <T> void removeFromDB(T... t) {
        EntityTransaction transaction = getEm().getTransaction();
        transaction.begin();
        try {
            for (T o : t) {
                getEm().remove(o);
            }
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    public List<Transaction> getTransactions(){
        TypedQuery<Transaction> query = getEm().createQuery("select tr from Transaction tr", Transaction.class);
        return query.getResultList();
    }

    public List<Transaction> getTransactionsByName(String username){
        TypedQuery<Transaction> query = getEm().createQuery("select tr from Transaction tr where tr.client.nickname = :name", Transaction.class);
        query.setParameter("name", username);
        return query.getResultList();
    }

    public EntityManager getEm() {
        return sessionDB.getEm();
    }
}
