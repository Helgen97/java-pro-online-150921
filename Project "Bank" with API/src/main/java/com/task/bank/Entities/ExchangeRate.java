package com.task.bank.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ExchangeRate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Currency", nullable = false)
    private String ccy;

    @Column(name = "Base_Currency")
    private String base_ccy;

    @Column(name = "Buy")
    private double buy;

    @Column(name = "Sale")
    private double sale;

    public ExchangeRate() {

    }

    public ExchangeRate(String currency,String baseCurrency, double buy, double sale) {
        this.ccy = currency;
        this.base_ccy = baseCurrency;
        this.buy = buy;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }


    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }
}
