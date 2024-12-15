package org.example.logic;

public class Budget {

    private double estimateAmount;
    private RangeTime rangeTime;

    public Budget(double estimateAmount, RangeTime rangeTime) {
        this.estimateAmount = estimateAmount;
        this.rangeTime = rangeTime;
    }

    public double getEstimateAmount() {
        return estimateAmount;
    }

    public Budget setEstimateAmount(double estimateAmount) {
        this.estimateAmount = estimateAmount;
        return this;
    }

    public RangeTime getRangeTime() {
        return rangeTime;
    }

    public Budget setRangeTime(RangeTime rangeTime) {
        this.rangeTime = rangeTime;
        return this;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "estimateAmount=" + estimateAmount +
                ", rangeTime=" + rangeTime +
                '}';
    }
}
