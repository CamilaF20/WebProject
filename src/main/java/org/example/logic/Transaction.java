package org.example.logic;

public class Transaction {
    private int idTransaction;
    private String date;
    private double value;
    private String description;
    private String category;

    public Transaction(int idTransaction, String date, double value, String description, String category) {
        this.idTransaction = idTransaction;
        this.date = date;
        this.value = value;
        this.description = description;
        this.category = category;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + idTransaction +
                ", date='" + date + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
