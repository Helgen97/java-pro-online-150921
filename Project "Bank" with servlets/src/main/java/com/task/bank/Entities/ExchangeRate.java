package com.task.bank.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ExchangeValues")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Currency", nullable = false)
    private String currencyName;

    @Column(name = "Value")
    private double value;

    public ExchangeRate() {

    }

    public ExchangeRate(String name, double value) {
        this.currencyName = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
