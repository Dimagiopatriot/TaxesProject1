package model.entities.taxes;

import model.entities.incomes.Income;
import model.entities.utils.Constants;

/**
 * Created by troll on 13.08.2017.
 */
public class Tax implements TaxCalculator{

    Income income;
    double taxPercent;
    double taxNeedToPay;

    Tax(Income income, double taxPercent){
        this.income = income;
        this.taxPercent = taxPercent;
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
}
