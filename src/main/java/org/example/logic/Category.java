package org.example.logic;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private double value;
    private List<Transaction> transactionList;

    public Category(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.transactionList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", transactionList=" + transactionList +
                '}';
    }
}
