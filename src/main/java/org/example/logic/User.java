package org.example.logic;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private double estimateAmount;
    private RangeTime rangeTime;
    private List<Category> categories;

    public User(int id, String name, double estimateAmount, RangeTime rangeTime) {
        this.id = id;
        this.name = name;
        this.estimateAmount = estimateAmount;
        this.rangeTime = rangeTime;
        this.categories = new ArrayList<>();
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

    public double getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(double estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public RangeTime getRangeTime() {
        return rangeTime;
    }

    public void setRangeTime(RangeTime rangeTime) {
        this.rangeTime = rangeTime;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", estimateAmount=" + estimateAmount +
                ", rangeTime=" + rangeTime +
                ", categories=" + categories +
                '}';
    }
}
