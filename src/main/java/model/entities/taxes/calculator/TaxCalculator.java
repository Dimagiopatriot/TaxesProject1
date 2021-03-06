package model.entities.taxes.calculator;

import model.entities.incomes.Income;
import model.entities.utils.Constants;

import java.util.Locale;

public class TaxCalculator implements TaxCalculatorInterface {

    @Override
    public double calculateTax(Income income, double taxPercent) {
        double taxNeedToPay = 0.;
        if (!income.isPerMonth()) {
            taxNeedToPay = (income.getIncome() * taxPercent) / Constants.HUNDRED;
        } else if (income.isPerMonth()) {
            taxNeedToPay = Constants.MONTHS_IN_YEAR * ((income.getIncome() * taxPercent) / Constants.HUNDRED);
        }
        taxNeedToPay = Double.valueOf(String.format(Locale.US, "%.2f", taxNeedToPay));
        return taxNeedToPay;
    }
}
