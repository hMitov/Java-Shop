package com.JavaSemProj;
import java.time.LocalDate;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CashDesk {
    private String number;
    public static Shop shop;
    private ShopEmployee shopEmployee;
    private Invoice invoice;
    private List<Goods> markedGoods;
    private static int counter = 0;
    private double clientMoney;


   private CashDesk() {}

    public CashDesk(String number, ShopEmployee shopEmployee) {
        this.markedGoods = new ArrayList<Goods>();
        this.number = number;
        this.shopEmployee = shopEmployee;
    }

    public String getNumber() {
        return number;
    }

    public void print() {
        for(Goods good : this.markedGoods)
            System.out.println(good);
    }

    public double getClientMoney() {
        return clientMoney;
    }

    public void setClientMoney(double clientMoney) {
        this.clientMoney = clientMoney;
    }

    public Shop getShop() { return shop; }

    public ShopEmployee getShopEmployee() {
        return shopEmployee;
    }

    public List<Goods> getMarkedGoods() {
        return markedGoods;
    }

    private void setShopEmployee(ShopEmployee shopEmployee) { this.shopEmployee = shopEmployee; }

    public static void setShop(Shop shop) { CashDesk.shop = shop; }


    public void setShopEmployee(ShopEmployee shopEmployee, List<CashDesk> cashDesks) {
        this.getShopEmployee().setDesk_id(null);
        for(CashDesk cashDesk : cashDesks) {
            if(shopEmployee.getDesk_id().equals(cashDesk.getNumber())){
                cashDesk.setShopEmployee(null);
                System.out.println("CashDesk number " + cashDesk.getNumber() + " is with no ShopEmployee, please call one!");
                int i = 0;
                CashDesk.shop.get_cashiers();
                Scanner input = new Scanner(System.in);

                try {
                    i = input.nextInt();
                    if (i <= 0 || i > CashDesk.shop.getShopEmployees().size())
                        throw new Exception("There IS NOT such a cashDesk!");
                    else if (CashDesk.shop.getShopEmployees().get(i - 1).getDesk_id() != null)
                        throw new Exception("This cashier HAS JUST STARTED working on the other CashDesk!");
                    else {
                        cashDesk.shopEmployee = CashDesk.shop.getShopEmployees().get(i - 1);
                        CashDesk.shop.getShopEmployees().get(i - 1).setDesk_id(cashDesk.getNumber());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        shopEmployee.setDesk_id(this.getNumber());
        this.shopEmployee = shopEmployee;
        System.out.println("Employee list after update");
        System.out.println(CashDesk.shop.getShopEmployees().toString());
        System.out.println(cashDesks.toString());
    }


    public void switchTwoCashDesksEmployees(CashDesk cashDesk) {
        if(this.getShopEmployee()!= null && cashDesk.getShopEmployee() != null) {
            CashDesk temp = new CashDesk();
            temp.setShopEmployee(this.getShopEmployee());
            this.setShopEmployee(cashDesk.getShopEmployee());
            cashDesk.setShopEmployee(temp.getShopEmployee());
        }
    }

    public void clearShopEmployee() {
        this.shopEmployee = null;
    }

    public boolean ifEquals(Goods good1, Goods good) {
        return good1.getName().equals(good.getName());
    }

    public void increaseQuantityInList(Goods good, int quantity) {
        good.setQuantity(good.getQuantity() + quantity);
    }

    public void decreaseQuantityInList(Goods good, int quantity) throws NotEnoughQuantityOfGoodException {
        if (good.getQuantity() >= quantity)
            good.setQuantity(good.getQuantity() - quantity);
        else {
            for(Goods d_good : CashDesk.shop.getDeliveredGoods()) {
                if (d_good.getId().equals(good.getId())) {
                    int res = 0;
                    res = quantity - d_good.getQuantity();
                    System.out.println("You want " + res + " more of " + good + " than what is in stock in the shop");
                }
            }
            throw new NotEnoughQuantityOfGoodException("Not enough quantity of this product!");
        }
    }

    public void addAGoodForBuying(Goods deliveredGood, int quantity) throws NotEnoughQuantityOfGoodException, ExpirationPeriodIsOFFException {
        int old_quantity = 0;
        if(!DefinePricesForShop.checkIfExpirationPeriodIsOFF(deliveredGood)) {
            boolean flag = false;
            if(this.markedGoods.isEmpty()){
                decreaseQuantityInList(deliveredGood, quantity);
                this.markedGoods.add(new MarkedGoods(deliveredGood.getId(), deliveredGood.getName(), deliveredGood.getFinalPrice(), quantity, deliveredGood.getTypeOfGood(), deliveredGood.getExpirationDate()));
            }
            else {
                for (Goods m_good : this.getMarkedGoods()) {
                    if (ifEquals(m_good, deliveredGood)) {
                        flag = true;
                        increaseQuantityInList(m_good, quantity);
                        decreaseQuantityInList(deliveredGood, quantity);
                        break;
                    }
                }

                if(!flag) {
                    decreaseQuantityInList(deliveredGood, quantity);
                    this.markedGoods.add(new MarkedGoods(deliveredGood.getId(), deliveredGood.getName(), deliveredGood.getFinalPrice(), quantity, deliveredGood.getTypeOfGood(), deliveredGood.getExpirationDate()));
                }
            }
        }
        else
            throw new ExpirationPeriodIsOFFException("Expiration period is off!");
    }

    public synchronized void addInvoice() {
        CashDesk.shop.getInvoices().add(new Invoice(LocalDateTime.now(), this,  this.markedGoods, this.getShopEmployee()));
    }

    public void clearMarkedGoods() {
        this.markedGoods.clear();
    }

    public void addProductsToSoldList(Goods markedGood) {
        boolean flag = false;
        if (CashDesk.shop.getSoldGoods().isEmpty()) {
            CashDesk.shop.getSoldGoods().add(new SoldGoods(markedGood.getId(), markedGood.getName(), markedGood.getFinalPrice(), markedGood.getQuantity(), markedGood.getTypeOfGood(), markedGood.getExpirationDate()));
            System.out.println(" added to MarkedList");
        }
        else {
            for (Goods s_good : CashDesk.shop.getSoldGoods()) {
                if (ifEquals(s_good, markedGood)) {
                    flag = true;
                    increaseQuantityInList(s_good, markedGood.getQuantity());
                    break;
                }
            }
            if (!flag) {
                CashDesk.shop.getSoldGoods().add(new SoldGoods(markedGood.getId(), markedGood.getName(), markedGood.getFinalPrice(), markedGood.getQuantity(), markedGood.getTypeOfGood(), markedGood.getExpirationDate()));
            }
        }
    }

    public void putMarkedProductsIntoDeliveryGoods(List<Goods> markedGoods) {
        for(Goods m_good : markedGoods) {
            boolean flag = false;
            for(Goods d_good : CashDesk.shop.getDeliveredGoods()) {
                if(m_good.getId().equals(d_good.getId())) {
                    d_good.setQuantity(d_good.getQuantity() + m_good.getQuantity());
                    flag = true;
                    break;
                }
            }
            if(!flag)
                CashDesk.shop.getDeliveredGoods().add(m_good);
        }
    }





    public void checkClientMoney(/*double value*/) throws NotEnoughClientMoney {
        double sum = 0.0;
        for(Goods m_good : this.getMarkedGoods())
            sum += m_good.getFinalPrice() * m_good.getQuantity();
        if (clientMoney >= sum) {
            addInvoice();
        }
        else {
            putMarkedProductsIntoDeliveryGoods(this.markedGoods);
            throw new NotEnoughClientMoney("Not enough money!");
        }
        clearMarkedGoods();
        setClientMoney(0);
    }



    public void synchronizationMethod() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    checkClientMoney();
                } catch (NotEnoughClientMoney e) {
                    e.printStackTrace();
                }            
            }
        };
        new Thread(runnable, this.getNumber()).start();
        Thread.currentThread().setName("cashDesk "+ this.getNumber());
    }



    @Override
    public String toString() {
        return "####CashDesk №" + number + "####" + "\n" +
                "|ShopEmployee|: " + "Name: " + shopEmployee.getName() + "   ID: " + shopEmployee.getId_cashier() + "\n" + "\n" + "\n";
    }
}
