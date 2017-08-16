package model.entities.taxes;

import model.entities.incomes.Income;

/**
 * Created by troll on 14.08.2017.
 */
public class GiftIncomeTax extends Tax{

    GiftIncomeTax(Income giftIncome, double taxPercent) {
        super(giftIncome, taxPercent);
    }
}
