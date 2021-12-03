package com.task.bank.DBOperation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SessionDB {
    private final static SessionDB sessionDB = new SessionDB();

    private final EntityManagerFactory emf;
    private final EntityManager em;

    private SessionDB(){
        emf = Persistence.createEntityManagerFactory("BankJPA");
        em = emf.createEntityManager();
    }

    public static SessionDB getInstance(){
        return sessionDB;
    }

    public EntityManager getEm() {
        return em;
    }
}
