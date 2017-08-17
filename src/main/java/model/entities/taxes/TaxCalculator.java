package model.entities.taxes;

import model.entities.incomes.Income;

/**
 * Created by troll on 13.08.2017.
 */
public interface TaxCalculator {

    void calculateTax(Income income);
}
