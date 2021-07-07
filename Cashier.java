package com.JavaSemProj;

public class Cashier implements ShopEmployee {
    private String name;
    private String id_cashier;
    private String desk_id;
    public static double salary;

    public Cashier(String name, String id_cashier)
    {
        this.name = name;
        this.id_cashier = id_cashier;
    }

    public String getName() {
        return name;
    }

    public String getId_cashier() {
        return id_cashier;
    }

    public static double getSalary() { return Cashier.salary; }
    public static void setSalary(double salary) {
        Cashier.salary = salary;
    }

    public String getDesk_id() { return desk_id; }

    public void setDesk_id(String desk_id) {
        this.desk_id = desk_id;
    }

    @Override
    public String toString() {
        return
                "-----Cashier-----" + "\n" +
                "|Name| " + name +
                "   |Id_cashier| " + id_cashier +
                "   |Desk_id| " + desk_id + "\n" + "\n";
    }
}
