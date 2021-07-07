package com.JavaSemProj;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shop {
    private String name;
    private DefinePricesForShop definePricesForShop;
    private List<Goods> deliveredGoods;
    private List<ShopEmployee> shopEmployees;
    private List<Invoice> invoices;
    private List<Goods> soldGoods;

    public Shop(String name) {
        this.name = name;
        this.deliveredGoods = new ArrayList<Goods>();
        this.shopEmployees = new ArrayList<ShopEmployee>();
        this.invoices = new ArrayList<Invoice>();
        this.soldGoods = new ArrayList<Goods>();
    }

    public String getName() { return name; }

    public DefinePricesForShop getDefinePricesForShop() {
        return definePricesForShop;
    }

    public List<Goods> getDeliveredGoods() {
        return this.deliveredGoods;
    }

    public void get_delivered_goods() {
        for(Goods good : this.deliveredGoods)
            System.out.println(good.toString());
    }

    public void get_cashiers() {
        for(ShopEmployee s : this.getShopEmployees())
            System.out.println(s.toString());
    }

    public List<ShopEmployee> getShopEmployees() { return shopEmployees; }

    public List<Goods> getSoldGoods() { return soldGoods; }

    public List<Invoice> getInvoices() { return invoices; }

    public void setShopEmployeesList(List<ShopEmployee> shopEmployees) {
        this.shopEmployees = shopEmployees;
    }

    public void setDefinePricesForShop(DefinePricesForShop definePricesForShop) {
        this.definePricesForShop = definePricesForShop;
    }

    public void addDeliveryGood(Goods good) throws ExpirationPeriodIsOFFException {

        //this.definePricesForShop.defineFinalPrice(good);
        boolean flag = false;
        for (Goods d_good : this.deliveredGoods) {
            if (d_good.getName().equals(good.getName())) {
                flag = true;
                d_good.quantity += good.getQuantity();
                break;
            }
        }
        if(!flag) {
            this.deliveredGoods.add(good);
        }
    }

    public double monthlyExpensesForShopEmployeeSalaries() {
        double sum = 0.0;
        for(ShopEmployee shopEmployee : this.getShopEmployees()) {
            sum += Cashier.getSalary();
        }
        return sum;
    }

    public double expensesForDeliveryOfGoods() {
        double sum = 0.0;
        for(Goods good : this.getDeliveredGoods()) {
            sum += good.getDeliveryPrice() * good.getQuantity();
        }
        return sum;
    }

    public double earnedMoneyFromGoods() {
        double sum = 0.0;
        for(Goods good : this.getSoldGoods()) {
            sum += good.getFinalPrice() * good.getQuantity() - good.getDeliveryPrice() * good.getQuantity();
        }
        return sum;
    }

    public double finalEarning() {
        return earnedMoneyFromGoods() - monthlyExpensesForShopEmployeeSalaries();
    }

    @Override
    public String toString() {
        return  "     ++++++++++++++++++++++++" + "\n" +
                "*************  Shop  **************" + "\n" +
                "     ++++++++++++++++++++++++" + "\n" + "\n" + "\n" +
                "|Name| " + name + "\n" + "\n" + "\n" +
                "=====DeliveredGoods=====" + "\n" + deliveredGoods + "\n" + "\n" + "\n" +
                "=====ShopEmployees=====" + "\n" + shopEmployees + "\n" + "\n" + "\n" +
                "=====Invoices=====" + "\n" + invoices + "\n" + "\n"+ "\n" +
                "=====SoldGoods=====" + "\n" + soldGoods + "\n" + "\n" + "\n";
    }
}
