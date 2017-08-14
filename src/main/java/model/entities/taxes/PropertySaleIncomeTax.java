package model.entities.taxes;

import model.entities.incomes.additional_incomes.PropertySaleIncome;

/**
 * Created by troll on 14.08.2017.
 */
public class PropertySaleIncomeTax extends Tax implements TaxCalculator {

    PropertySaleIncomeTax(PropertySaleIncome propertySaleIncome, double taxPercent) {
        super(propertySaleIncome, taxPercent);
    }
}
