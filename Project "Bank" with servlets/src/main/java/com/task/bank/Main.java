package com.task.bank;

import com.task.bank.Entities.ExchangeRate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BankJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        ExchangeRate exchangeRate = new ExchangeRate("USD", 27.8);
        ExchangeRate exchangeRate1 = new ExchangeRate("UAH", 1.0);
        ExchangeRate exchangeRate2 = new ExchangeRate("EUR", 30.59);


        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(exchangeRate);
        entityManager.persist(exchangeRate1);
        entityManager.persist(exchangeRate2);
        entityTransaction.commit();
    }
}
