package com.task.bank.DBOperation;

import com.task.bank.Entities.Client;
import com.task.bank.Entities.Transaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionOperation extends BasicOperations {

    public List<Transaction> getTransactionsByValueRange(String username, String currencyName, double from, double to) {
        try {
            getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
            Root<Transaction> transactionRoot = criteriaQuery.from(Transaction.class);

            ParameterExpression<Double> fromParameter = criteriaBuilder.parameter(Double.class);
            ParameterExpression<Double> toParameter = criteriaBuilder.parameter(Double.class);
            ParameterExpression<Client> client = criteriaBuilder.parameter(Client.class);

            criteriaQuery.select(transactionRoot).where((
                    criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("currencyName"), currencyName),
                            criteriaBuilder.equal(transactionRoot.get("client"), client),
                            criteriaBuilder.between(transactionRoot.get("value"), fromParameter, toParameter))));
            TypedQuery<Transaction> query = getEm().createQuery(criteriaQuery);
            query.setParameter(fromParameter, from);
            query.setParameter(toParameter, to);
            query.setParameter(client, getClientByName(username));

            List<Transaction> transactions = query.getResultList();
            getEm().getTransaction().commit();
            return transactions;
        } catch (Exception ex) {
            getEm().getTransaction().rollback();
            return null;
        }
    }

    public List<Transaction> getTransactionsByDateRange(String username, String dateFrom, String dateTo) {
        getEm().getTransaction().begin();
        try {
            CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
            Root<Transaction> transactionRoot = criteriaQuery.from(Transaction.class);

            ParameterExpression<Client> client = criteriaBuilder.parameter(Client.class);
            ParameterExpression<Date> fromDate = criteriaBuilder.parameter(Date.class);
            ParameterExpression<Date> toDate = criteriaBuilder.parameter(Date.class);

            criteriaQuery.select(transactionRoot).where(criteriaBuilder.and(
               criteriaBuilder.equal(transactionRoot.get("client"), client),
               criteriaBuilder.greaterThanOrEqualTo(transactionRoot.get("date"), fromDate)),
                    criteriaBuilder.lessThanOrEqualTo(transactionRoot.get("date"), toDate));

            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);

            TypedQuery<Transaction> query = getEm().createQuery(criteriaQuery);
            query.setParameter(client, getClientByName(username));
            query.setParameter(fromDate, from);
            query.setParameter(toDate, to);

            List<Transaction> transactions = query.getResultList();
            getEm().getTransaction().commit();
            return transactions;
        } catch (Exception ex) {
            getEm().getTransaction().rollback();
            return null;
        }

    }
}
