package com.JavaSemProj;

import java.time.LocalDate;

public class MarkedGoods extends Goods {
    private String id_markedGood;
    public MarkedGoods(String id, String name, double finalPrice, int quantity, IsEatable isEatable, LocalDate localDate) {
        super(name);
        this.quantity = quantity;
        this.finalPrice = finalPrice;
        this.id_markedGood = id;
        this.typeOfGood = isEatable;
        this.expirationDate = localDate;
    }

    public String getId() { return id_markedGood; }

    @Override
    public double getDeliveryPrice() { return 0; }

    public IsEatable getTypeOfGood() {
        return typeOfGood;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return  "Name: " + name +
                "  FinalPrice: " + finalPrice +
                "  Quantity: " + quantity + "\n";
    }
}
