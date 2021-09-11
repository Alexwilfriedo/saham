package com.digital.model.response;

public class MonthAndAmount {
    private String month;
    private double amount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public MonthAndAmount(String month, double amount) {
        this.month = month;
        this.amount = amount;
    }
}
