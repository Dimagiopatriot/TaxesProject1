package model.entities.taxes;

import model.entities.incomes.additional_incomes.ChildrenDiscountIncome;
import model.entities.utils.Constants;

/**
 * Created by troll on 14.08.2017.
 */
public class ChildrenDiscountIncomeTax extends Tax {

    ChildrenDiscountIncomeTax(ChildrenDiscountIncome childrenDiscountIncome, double taxPercent){
        super(childrenDiscountIncome, taxPercent);
    }
}
