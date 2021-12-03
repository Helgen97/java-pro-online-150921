package com.task.bank.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Client client;

    @Column(name = "Currency")
    private String currencyName;

    @Column(name = "Value")
    private double value;

    public Account() {

    }

    public Account(String currencyName) {
        this.currencyName = currencyName;
    }

    public void addFunds(double value) {
        this.value += value;
    }

    public void decreaseFunds(double value) {
        this.value -= value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", value=" + value +
                '}';
    }
}
