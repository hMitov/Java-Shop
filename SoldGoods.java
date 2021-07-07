package com.JavaSemProj;

import java.time.LocalDate;

public class SoldGoods extends Goods {
    private String id_soldGood;
    public SoldGoods(String id, String name, double finalPrice, int quantity, IsEatable isEatable, LocalDate localDate) {
        super(name);
        this.id_soldGood = id;
        this.finalPrice = finalPrice;
        this.quantity = quantity;
        this.typeOfGood = isEatable;
        this.expirationDate = localDate;
    }

    @Override
    public double getDeliveryPrice() { return 0; }

    public IsEatable getTypeOfGood() {
        return typeOfGood;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getId() { return id_soldGood; }

    @Override
    public String toString() {
        return
                "Name: " + name +
                "  FinalPrice: " + finalPrice +
                "  Quantity: " + quantity + "\n";
    }
}
