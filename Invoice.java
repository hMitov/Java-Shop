package com.JavaSemProj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.io.FileWriter;

public class Invoice {
    private String id_invoice;
    private static int id = 0;
    private LocalDateTime dateAndTime;
    private ShopEmployee shopEmployee;
    private List<Goods> soldGoods;
    private CashDesk cashDesk;

    public Invoice(LocalDateTime dateAndTime, CashDesk cashDesk, List<Goods> markedGoods, ShopEmployee shopEmployee) {
        this.soldGoods = new ArrayList<Goods>();
        Invoice.id +=1;
        this.id_invoice = String.valueOf(Invoice.id);
        this.dateAndTime = dateAndTime;
        this.cashDesk = cashDesk;
        for(Goods good : markedGoods) {
            System.out.println("Current Thread Name- " + Thread.currentThread().getName());
            System.out.println(good.toString());
            this.cashDesk.addProductsToSoldList(good);
            this.soldGoods.add(good);
        }
        this.shopEmployee = shopEmployee;
        String filename = "files/Invoice" + String.valueOf(this.id_invoice) + ".txt";
        try(FileWriter fout = new FileWriter(filename)) {
            fout.write(this.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!" + e);
        } catch (IOException e) {
            System.out.println("IO Exception!" + e);
        }
        System.out.println(toString());
    }

    public String getId_invoice() {
        return id_invoice;
    }

    public LocalDateTime getDate() {
        return dateAndTime;
    }

    public ShopEmployee getShopEmployee() {
        return shopEmployee;
    }

    public double getSoldListPrice() {
        double sum = 0.0;
        for(Goods s_good : this.soldGoods)
            sum += s_good.getFinalPrice() * s_good.getQuantity();
        return sum;
    }


    @Override
    public String toString() {
        return ".....Invoice....." + "\n" +
                "   |Id_invoice| " + id_invoice + "\n" +
                "   |DateAndTime| " + dateAndTime + "\n" +
                "   |ShopEmployee| " + shopEmployee.getName() + "\n" +
                "   |SoldGoods| " + "\n" + soldGoods + "\n" +
                "   ...Price... " + this.getSoldListPrice() + "\n" + "\n" + "\n";
    }
}
