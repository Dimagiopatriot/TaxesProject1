package model.entities.taxes;

import model.entities.incomes.Income;

/**
 * Created by troll on 14.08.2017.
 */
public class PropertySaleIncomeTax extends Tax implements TaxCalculator {

    PropertySaleIncomeTax(Income propertySaleIncome, double taxPercent) {
        super(propertySaleIncome, taxPercent);
    }
}
