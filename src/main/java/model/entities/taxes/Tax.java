package model.entities.taxes;

import model.entities.incomes.Income;
import model.entities.utils.Constants;

/**
 * Created by troll on 13.08.2017.
 */
public class Tax implements TaxCalculator{

    private int id;
    double taxPercent;
    double taxNeedToPay;
    private String name;

    public Tax(double taxPercent){
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

    public void calculateTax(Income income) {
        if (!income.isPerMonth()) {
            taxNeedToPay = (income.getIncome() * taxPercent) / Constants.HUNDRED;
        } else if (income.isPerMonth()){
            taxNeedToPay = Constants.MONTHS_IN_YEAR * ((income.getIncome() * taxPercent) / Constants.HUNDRED);
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
