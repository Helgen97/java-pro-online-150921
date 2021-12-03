package com.task.bank.DBOperation;

import com.task.bank.Entities.Client;
import com.task.bank.Entities.ExchangeRate;
import jakarta.persistence.*;

import java.util.List;

public class BasicOperations {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public BasicOperations() {
        emf = Persistence.createEntityManagerFactory("BankJPA");
        em = emf.createEntityManager();
    }

    public <T> void addToDB(T t) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(t);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        }
    }

    public boolean isClientExist(String username) {
        Client client = getClientByName(username);
        return client != null;
    }

    public List<Client> getClients() {
        TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);

        return query.getResultList();
    }

    public Client getClientByName(String username) {
        try {
            TypedQuery<Client> query = em.createQuery(
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
            TypedQuery<ExchangeRate> query = em.createQuery(
                    "SELECT ex FROM ExchangeRate ex WHERE ex.currencyName = :name", ExchangeRate.class
            );
            query.setParameter("name", currencyName);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }

    public EntityManager getEm() {
        return em;
    }
}
