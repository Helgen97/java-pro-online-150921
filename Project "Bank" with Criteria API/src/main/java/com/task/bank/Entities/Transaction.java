package com.task.bank.Entities;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Transaction_Client")
    private Client client;

    @Column(name = "Currency")
    private String currencyName;

    @Column(name = "Value")
    private double value;

    @Column(name = "Receiver")
    private String receiverName;

    @Column(name = "Currency_Receiver")
    private String receiveCurrency;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Date")
    private Date date;

    public Transaction() {

    }

    public Transaction(String currencyName, double value, String receiverName, String receiveCurrency, String comment) {
        this.currencyName = currencyName;
        this.value = value;
        this.receiverName = receiverName;
        this.receiveCurrency = receiveCurrency;
        this.comment = comment;
        this.date = new Date();
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

    public void setClient(Client client) {
        this.client = client;
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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiveCurrency() {
        return receiveCurrency;
    }

    public void setReceiveCurrency(String receiveCurrency) {
        this.receiveCurrency = receiveCurrency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", value=" + value +
                ", receiverName='" + receiverName + '\'' +
                ", receiveCurrency='" + receiveCurrency + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
