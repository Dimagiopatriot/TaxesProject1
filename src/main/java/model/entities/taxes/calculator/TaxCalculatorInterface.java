package model.entities.taxes.calculator;

import model.entities.incomes.Income;

/**
 * Created by troll on 13.08.2017.
 */
public interface TaxCalculatorInterface {

    double calculateTax(Income income, double taxPercent);
}
