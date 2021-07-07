package com.JavaSemProj;
import java.io.IOException;
import java.net.http.WebSocketHandshakeException;
import java.security.spec.RSAOtherPrimeInfo;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private List<ShopEmployee> shopEmployees;
    private Shop shop;
    private List<CashDesk> cashDesks;
    private String managerPassword;
    private int countMenuCalls;
    private List<Goods> goodsInBasket;


    public Shop getShop() {
        return shop;
    }


    public List<CashDesk> getCashDesks() {
        return cashDesks;
    }


    public Menu() {
    }


    public void shopPreparation() {
        goodsInBasket = new ArrayList<>();
        //Initialize Shop
        this.shop = new Shop("Organic shop");
        this.managerPassword = "6598_@3#";
        this.countMenuCalls = 0;

        //Initialize employees

        ShopEmployee shopEmployee1 = new Cashier("Ivan", "1");
        ShopEmployee shopEmployee2 = new Cashier("Milena", "2");
        ShopEmployee shopEmployee3 = new Cashier("Petar", "3");
        ShopEmployee shopEmployee4 = new Cashier("Evgenia", "4");
        Cashier.setSalary(1200);
        shopEmployee1.setDesk_id("1");
        shopEmployee2.setDesk_id("2");


        //put cashiers into shop cashiers list

        List<ShopEmployee> cashiers = new ArrayList<ShopEmployee>();
        cashiers.add(shopEmployee1);
        cashiers.add(shopEmployee2);
        cashiers.add(shopEmployee3);
        cashiers.add(shopEmployee4);

        this.shop.setShopEmployeesList(cashiers);

        //Define prices for stocks in shop
        DefinePricesForShop definePricesForShop = new DefinePricesForShop();
        this.shop.setDefinePricesForShop(definePricesForShop);
        this.shop.getDefinePricesForShop().setMarkUpPercentage(0.25, 0.30);
        this.shop.getDefinePricesForShop().setDiscountPercentage(0.05);
        this.shop.getDefinePricesForShop().setDaysUntilExpiration_Deadline(7);

        //Initialize stocks

        List<Goods> deliveredGoods = new ArrayList<Goods>();
        Goods good1 = new DeliveredGoods("Geer cow Valona Ghee", 100, 5, IsEatable.EATABLE, LocalDate.of(2021, 6, 10));
        Goods good2 = new DeliveredGoods("Bhens Cream Ghee", 110, 7, IsEatable.EATABLE, LocalDate.of(2021, 6, 10));
        Goods good3 = new DeliveredGoods("Peanut Oil", 50, 10, IsEatable.EATABLE, LocalDate.of(2021, 11, 11));
        Goods good4 = new DeliveredGoods("Black Sesame Oil", 40, 4, IsEatable.EATABLE, LocalDate.of(2021, 9, 20));
        Goods good5 = new DeliveredGoods("White Sesame Oil", 87, 9.6, IsEatable.EATABLE, LocalDate.of(2021, 7, 15));
        Goods good6 = new DeliveredGoods("Bhens Cream Ghee", 98, 12.5, IsEatable.EATABLE, LocalDate.of(2021, 8, 9));

        //adding stocks

        deliveredGoods.add(good1);
        deliveredGoods.add(good2);
        deliveredGoods.add(good3);
        deliveredGoods.add(good4);
        deliveredGoods.add(good5);
        deliveredGoods.add(good6);

        //defining final price for stocks

        for (Goods good : deliveredGoods) {
            try {
                definePricesForShop.defineFinalPrice(good);
            } catch (ExpirationPeriodIsOFFException e) {
                e.printStackTrace();
            }
        }

        for (Goods good : deliveredGoods) {
            try {
                this.shop.addDeliveryGood(good);
            } catch (ExpirationPeriodIsOFFException e) {
                e.printStackTrace();
            }
        }

        //Initialize cashDesks

        this.cashDesks = new ArrayList<CashDesk>();
        CashDesk.setShop(this.shop);
        CashDesk cashDesk1 = new CashDesk("1", shopEmployee1);
        CashDesk cashDesk2 = new CashDesk("2", shopEmployee2);
        this.cashDesks.add(cashDesk1);
        this.cashDesks.add(cashDesk2);
    }


    public int client_OR_manager() {
        String str;
        do {
            System.out.println("\n\n\n");
            System.out.println("              Hello, please PRESS 0 if you are a CLIENT or");
            System.out.println("              PRESS 1 if MANAGER!");
            Scanner input = new Scanner(System.in);
            str = input.nextLine();
        } while (!str.equals("0") && !str.equals("1"));
        if (str.equals("1"))
            return 1;
        else
            return 0;
    }


    public void operationsForManager() {
        boolean more = true;
        int count = 0;
        Scanner input = new Scanner(System.in);
        while (more) {
            System.out.println("                 Please PRESS a number from below to MAKE AN OPERATION: ");
            System.out.println("PRESS 0 TO EXIT 'MANAGER MENU'");
            System.out.println("PRESS 1 for checking the CASH_DESK EMPLOYEE");
            System.out.println("PRESS 2 for checking GOODS IN STOCK in the shop");
            System.out.println("PRESS 3 for checking SOLD GOODS in the shop");
            System.out.println("PRESS 4 for checking INVOICES in the shop");
            System.out.println("PRESS 5 for checking EMPLOYEES working in the shop");
            System.out.println("PRESS 6 for checking MARK_UP and DISCOUNT PERCENTAGE and EXPIRATION DEADLINE, in the shop");
            System.out.println("PRESS 7 for checking CASHIER SALARY in the shop");
            System.out.println("PRESS 8 for checking CHANGE CASHIER SALARY in the shop");


            System.out.println("PRESS 9 for setting cashDesk employee");
            System.out.println("PRESS 10 for switching two desk employees");

            System.out.println("PRESS 11 for changing the markUp percentage in shop");
            System.out.println("PRESS 12 for changing the discount percentage in shop");
            System.out.println("PRESS 13 for changing the expirationPeriodDeadline in shop");

            System.out.println("PRESS 14 for showing monthly expenses for shopEmployee salaries");
            System.out.println("PRESS 15 for showing the expenses for delivery of goods");
            System.out.println("PRESS 16 for showing the earned money from sold goods");
            System.out.println("PRESS 17 for showing the final earning of shop");
            System.out.println("PRESS 18 to add a new Cashier");
            System.out.println("!!!PRESS 19 show shop info!!!");


            int i = 0;
            try {
                i = input.nextInt();
                input.nextLine();
                if (i > 19)
                    throw new IndexOutOfBoundsException("There IS NO such an operation!");
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            if (i == 0) {
                more = false;
                Menu();
            } else {
                count += 1;
                switch (i) {
                    case 1:
                        System.out.println("Shop employee of CashDesk 1: " + this.cashDesks.get(0).getShopEmployee());
                        System.out.println("Shop employee of CashDesk 2: " + this.cashDesks.get(1).getShopEmployee());
                        break;
                    case 2:
                        System.out.println(this.shop.getDeliveredGoods());
                        break;
                    case 3:
                        System.out.println(this.shop.getSoldGoods());
                        break;
                    case 4:
                        System.out.println(this.shop.getInvoices());
                        break;
                    case 5:
                        System.out.println(this.shop.getShopEmployees());
                        break;
                    case 6:
                        System.out.println(this.shop.getDefinePricesForShop());
                        break;

                    case 7:
                        System.out.println("Cashier salary is: " + Cashier.getSalary());
                        break;

                    case 8:

                        double salary = 0.0;
                        System.out.print("Please ENTER the new salary: ");
                        try {
                            salary = input.nextInt();
                            if (salary <= 0)
                                throw new Exception("Salary CAN NOT be a NEGATIVE or ZERO number!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Cashier.setSalary(salary);
                        System.out.println("Cashier salary is: " + Cashier.getSalary());
                        break;

                    case 9:
                        System.out.println("Please ENTER a desk from the list below: ");
                        System.out.println(this.cashDesks.toString());
                        int d;
                        int em;
                        try {
                            d = 0;
                            em = 0;
                            d = input.nextInt();
                            if (d <= 0 || d > this.cashDesks.size())
                                throw new Exception("NOT such a desk in the shop!");
                            System.out.println("Please ENTER an employee from below: ");
                            System.out.println(this.shop.getShopEmployees().toString());
                            em = input.nextInt();
                            /*if(em <= 0 || em > this.shopEmployees.size())
                                throw new Exception("NOT SUCH an employee in the shop!");*/
                            this.cashDesks.get(d - 1).setShopEmployee(this.shop.getShopEmployees().get(em - 1), this.cashDesks);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 10:
                        System.out.println("Please ENTER a desk from the list below: ");
                        System.out.println(this.cashDesks.toString());
                        d = 0;
                        try {
                            d = input.nextInt();
                            if (d <= 0 || d > this.cashDesks.size())
                                throw new Exception("NOT such a desk in the shop!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println("Please ENTER another desk from the list below: ");
                        System.out.println(this.cashDesks.toString());
                        int d1 = 0;
                        try {
                            d1 = input.nextInt();
                            if (d1 <= 0 || d1 > this.cashDesks.size())
                                throw new Exception("NOT such a desk in the shop!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.cashDesks.get(d - 1).switchTwoCashDesksEmployees(this.cashDesks.get(d1 - 1));
                        System.out.println("CashDesks after update");
                        System.out.println(this.cashDesks.toString());
                        break;

                    case 11:
                        System.out.println("ENTER a new markUp percentage for EATABLE and for NOT_EATABLE products : ");
                        double per1 = 0;
                        double per2 = 0;
                        try {
                            per1 = input.nextInt();
                            per2 = input.nextInt();
                            if (per1 <= 0.0 || per1 / 100 > 0.5 || per2 <= 0.0 || per2 / 100 > 0.5)
                                throw new Exception("Entered percentage is either LESS or EQUAL to ZERO or TOO LARGE!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.shop.getDefinePricesForShop().setMarkUpPercentage(per1, per2);
                        System.out.println("Mark up percentage for EATABLE is: " + this.shop.getDefinePricesForShop().getMarkUpPercentageForEATABLE());
                        System.out.println("Mark up percentage for NOT_EATABLE is: " + this.shop.getDefinePricesForShop().getMarkUpPercentageForNOTEATABLE());
                        break;

                    case 12:
                        System.out.println("ENTER a new discount percentage in shop when incoming expirationDeadline: ");
                        per1 = 0;
                        try {
                            per1 = input.nextInt();
                            if (per1 <= 0.0 || per1 / 100 > 0.2)
                                throw new Exception("Entered percentage is either LESS or EQUAL to ZERO or TOO LARGE!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.shop.getDefinePricesForShop().setDiscountPercentage(per1);
                        System.out.println("Discount percentage in shop is: " + this.shop.getDefinePricesForShop().getDiscountPercentage());
                        break;

                    case 13:
                        System.out.println("ENTER number of days UNTIL DAYS_UNTIL_EXPIRATION DEADLINE  : ");
                        int days = 0;
                        try {
                            days = input.nextInt();
                            if (days < 0 || days > 10)
                                throw new Exception("Entered number of days is eather LESS than ZERO or TOO LARGE period of time !");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.shop.getDefinePricesForShop().setDaysUntilExpiration_Deadline(days);
                        System.out.println("Deadline daysUntilExpiration: " + this.shop.getDefinePricesForShop().getDaysUntilExpiration_Deadline());
                        break;

                    case 14:
                        System.out.println("Monthly expenses for shopEmployee salaries are: " + this.shop.monthlyExpensesForShopEmployeeSalaries());
                        break;

                    case 15:
                        System.out.println("Expenses for delivery of goods: " + this.shop.expensesForDeliveryOfGoods());
                        break;

                    case 16:
                        System.out.println("Earned money from sold goods: " + this.shop.earnedMoneyFromGoods());
                        break;

                    case 17:
                        System.out.println("Final earning in shop: " + this.shop.finalEarning());
                        break;

                    case 18:
                        String name;
                        System.out.println("Please enter Cashier's Name and it's ID");
                        name = input.nextLine();
                        try {
                            Cashier c = new Cashier(name, String.valueOf(this.shop.getShopEmployees().size() + 1));
                            this.shop.getShopEmployees().add(c);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        CashDesk.shop.get_cashiers();
                        break;

                    case 19:
                        System.out.println(this.shop.toString());
                        break;
                }
            }
        }
        input.close();
    }


    public void listWithDeliveredItems(CashDesk cashDesk) {
        System.out.println("\n\n\n\n");
        System.out.println("                                                      .... W  E  L  C  O  M  E     T O     O  U  R     S  H  O  P ! ....");
        System.out.println("                                                      ...................................................................\n\n\n\n");
        System.out.println("                                                                               CASH_DESK №" + (cashDesk.getNumber()));
        System.out.println("                                                                               ------------\n\n\n\n");
        System.out.println(shop.getDeliveredGoods());
        Scanner input = new Scanner(System.in);
        boolean more = true;
        int count = 0;
        int i = 0, n = 0;
        while (more) {
            System.out.println("\n\n\n");
            System.out.println("                                         Please ENTER a product ID and it's quantity OR PRESS 0 IF YOU DO NOT WANT MORE STOCKS");
            System.out.println("                                         -------------------------------------------------------------------------------------");
            System.out.print("Id: ");
            try {
                i = input.nextInt();
                if (i < 0 || i > shop.getDeliveredGoods().size())
                    throw new Exception("There IS NOT such an delivered object");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 0) {
                more = false;
            } else {
                System.out.print("Quantity: ");
                n = input.nextInt();
                count += 1;

                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6: {
                        try {
                            cashDesk.addAGoodForBuying(shop.getDeliveredGoods().get(i - 1), n);
                        } catch (NotEnoughQuantityOfGoodException | ExpirationPeriodIsOFFException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

        if (i == 0 && count == 0) {
            Menu();
        } else {

            double money;
            System.out.print("Please ENTER your money amount: ");
            money = input.nextDouble();
            cashDesk.setClientMoney(money);
            input.nextLine();
        }

        System.out.println(CashDesk.shop.toString());
        int exit;
        if(cashDesk.getNumber().equals("2")) {
            System.out.println("PRESS 0 IF YOU WANT TO EXIT PROGRAM or any other BUTTON to continue in programme");
            exit = input.nextInt();
            input.nextLine();
            if(exit == 0)
                return;
            Menu();
        }
        input.close();
    }












    public void Menu(){

        System.out.println("\n\n\n");
        System.out.println("                                                                 *********************************************");
        System.out.println("                                                                *********** Welcome to " + getShop().getName() + " ***********");
        System.out.println("                                                                 *********************************************");
        Scanner input = new Scanner(System.in);
        int count = 0;
        int exit = 0;
        if(this.countMenuCalls > 0) {
            System.out.println("PRESS 0 IF YOU WANT TO EXIT PROGRAM or any other BUTTON to continue in programme");
            exit = input.nextInt();
            input.nextLine();
            if(exit == 0)
                return;
        }
        if(client_OR_manager() == 1) {
            this.countMenuCalls +=1;
            System.out.println("\t.....Please ENTER manager password.....");
            String str;
            str = input.nextLine();
            //////
            while(!str.equals("uu") && count <=3 && !str.equals(String.valueOf(0))) {
                System.out.println("INVALID password, please TRY AGAIN or PRESS 0 FOR BACK!");
                str = input.nextLine();
                count += 1;
            }
            if(count > 3 || str.equals(String.valueOf(0)))
                Menu();
            else
                operationsForManager();
        }
        else {
            this.countMenuCalls +=1;
            for(int i=0; i< this.cashDesks.size(); i++) {
                System.out.println("Please ENTER products to cashDesk number " + cashDesks.get(i).getNumber());
                listWithDeliveredItems(cashDesks.get(i));
            }

            cashDesks.get(0).synchronizationMethod();


            cashDesks.get(1).synchronizationMethod();

            System.out.println(CashDesk.shop.toString());

        }
    }
}
