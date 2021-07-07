package com.JavaSemProj;

import java.time.LocalDate;

public class DeliveredGoods extends Goods {
    private String id_deliveredGood;
    private static int id = 0;

    public DeliveredGoods(String name) {super(name);}

    public DeliveredGoods(String name, int quantity, double deliveryPrice,  IsEatable typeOfGood, LocalDate expirationDate)
    {
        super(name);
        this.quantity = quantity;
        DeliveredGoods.id += 1;
        this.id_deliveredGood = String.valueOf(DeliveredGoods.id);
        this.deliveryPrice = deliveryPrice;
        this.typeOfGood = typeOfGood;
        this.expirationDate = expirationDate;
    }

    public double getDeliveryPrice() { return deliveryPrice; }

    public IsEatable getTypeOfGood() {
        return typeOfGood;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getId() {
        return id_deliveredGood;
    }

    @Override
    public String toString() {
        return
                "Id_Good: " + id_deliveredGood +
                "   Name: " + name +
                "   FinalPrice: " + finalPrice +
                "   Quantity: " + quantity +
                "   DeliveryPrice: " + deliveryPrice +
                "   TypeOfGood: " + typeOfGood +
                "   ExpirationDate: " + expirationDate + "\n";

    }
}
