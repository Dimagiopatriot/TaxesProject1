package model.entities.taxes;

import model.entities.incomes.Income;
import model.entities.utils.Constants;

/**
 * Created by troll on 13.08.2017.
 */
public class Tax implements TaxCalculator{

    private int id;
    Income income;
    double taxPercent;
    double taxNeedToPay;
    private String name;

    Tax(Income income, double taxPercent){
        this.income = income;
        this.taxPercent = taxPercent;
        name = getClass().getSimpleName();
    }

    public double getTaxNeedToPay() {
        return taxNeedToPay;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public void calculateTax() {
        taxNeedToPay = (income.getIncome() * taxPercent) / Constants.HUNDRED;
    }

    public String getName() {
        return name;
    }
}
