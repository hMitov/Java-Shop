package com.JavaSemProj;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DefinePricesForShop {
    private double markUpPercentageForEATABLE;
    private double markUpPercentageForNOTEATABLE;
    private double discountPercentage;
    private int daysUntilExpiration_Deadline;

    public DefinePricesForShop(){}

    public void setMarkUpPercentageForEATABLE(double markUpPercentageForEATABLE) {
        this.markUpPercentageForEATABLE = markUpPercentageForEATABLE;
    }

    public void setMarkUpPercentageForNOTEATABLE(double markUpPercentageForNOTEATABLE) {
        this.markUpPercentageForNOTEATABLE = markUpPercentageForNOTEATABLE;
    }

    public void setMarkUpPercentage(double EATABLE_products, double NOT_EATABLE_products) {
        setMarkUpPercentageForEATABLE(EATABLE_products);
        setMarkUpPercentageForNOTEATABLE(NOT_EATABLE_products);
        IsEatable.EATABLE.setPercentage(this.markUpPercentageForEATABLE);
        IsEatable.NOT_EATABLE.setPercentage(this.markUpPercentageForNOTEATABLE);
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setDaysUntilExpiration_Deadline(int daysUntilExpiration_Deadline) {
        this.daysUntilExpiration_Deadline = daysUntilExpiration_Deadline;
    }

    public double getMarkUpPercentageForEATABLE() {
        return markUpPercentageForEATABLE;
    }

    public double getMarkUpPercentageForNOTEATABLE() {
        return markUpPercentageForNOTEATABLE;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public int getDaysUntilExpiration_Deadline() {
        return daysUntilExpiration_Deadline;
    }

    public boolean checkIfIsEatable(Goods good) {
        return good.getTypeOfGood().equals(IsEatable.EATABLE);
    }

    public boolean checkIfExpirationPeriodInShortage(Goods good) {
        return ChronoUnit.DAYS.between(LocalDate.now(), good.getExpirationDate()) < this.daysUntilExpiration_Deadline;
    }

    public static boolean checkIfExpirationPeriodIsOFF(Goods good) {
        long diff = 0;
        diff = ChronoUnit.DAYS.between(LocalDate.now(), good.getExpirationDate());
        if(diff >= 0)
            return false;
        else
            return true;
    }

    public double PriceWithMarkUp(Goods good, IsEatable object) {
        double temp = good.getDeliveryPrice() + good.getDeliveryPrice() * object.getPercentage();
        return temp;
    }

    public double PriceOffIfExpirationPeriodInShortage(Goods good) throws ExpirationPeriodIsOFFException {
        if(checkIfExpirationPeriodInShortage(good)) {
            if(!DefinePricesForShop.checkIfExpirationPeriodIsOFF(good)){
                double temp = 0.0;
                temp = good.finalPrice - good.finalPrice * this.getDiscountPercentage();
                return temp;
            }
            else
                throw new ExpirationPeriodIsOFFException("Expiration period is off! This good will not be added!");
        }
        else
            return 0;
    }


    public void defineFinalPrice(Goods good) throws ExpirationPeriodIsOFFException {
        good.finalPrice = PriceWithMarkUp(good, good.getTypeOfGood()) - PriceOffIfExpirationPeriodInShortage(good);
    }

    @Override
    public String toString() {
        return "_____PriceRegulationsInShop_____" + "\n" +
                "|MarkUpPercentageForEATABLE| " + markUpPercentageForEATABLE +
                "   |MarkUpPercentageForNOTEATABLE| " + markUpPercentageForNOTEATABLE +
                "   |DiscountPercentage| " + discountPercentage +
                "   |DaysUntilExpiration_Deadline| " + daysUntilExpiration_Deadline + "\n";
    }
}
