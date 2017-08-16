package model.entities.taxes;

import model.entities.incomes.Income;

/**
 * Created by troll on 14.08.2017.
 */
public class ChildrenDiscountIncomeTax extends Tax {

    ChildrenDiscountIncomeTax(Income childrenDiscountIncome, double taxPercent){
        super(childrenDiscountIncome, taxPercent);
    }
}
