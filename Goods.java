package com.JavaSemProj;

import java.time.LocalDate;

abstract public class Goods {
    protected String name;
    protected double finalPrice;
    protected int quantity;
    protected double deliveryPrice;
    protected IsEatable typeOfGood;
    protected LocalDate expirationDate;

    public Goods(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    abstract public double getDeliveryPrice();
    abstract public String getId();
    abstract public IsEatable getTypeOfGood();
    abstract public LocalDate getExpirationDate();
    abstract public String toString();
}
