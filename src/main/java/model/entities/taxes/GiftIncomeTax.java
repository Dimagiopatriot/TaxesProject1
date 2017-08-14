package model.entities.taxes;

import model.entities.incomes.additional_incomes.GiftIncome;

/**
 * Created by troll on 14.08.2017.
 */
public class GiftIncomeTax extends Tax{

    GiftIncomeTax(GiftIncome giftIncome, double taxPercent) {
        super(giftIncome, taxPercent);
    }
}
