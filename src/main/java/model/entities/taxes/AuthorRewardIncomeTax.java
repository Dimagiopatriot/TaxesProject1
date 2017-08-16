package model.entities.taxes;

import model.entities.incomes.Income;
import model.entities.utils.Constants;

/**
 * Created by troll on 14.08.2017.
 */
public class AuthorRewardIncomeTax extends Tax {

    private double militaryCharge;

    AuthorRewardIncomeTax(Income authorRewardIncome, double taxPercent, double militaryCharge) {
        super(authorRewardIncome, taxPercent);
        this.militaryCharge = militaryCharge;
    }

    public void calculateTax() {
        taxNeedToPay = ((income.getIncome() * taxPercent) + (income.getIncome() * militaryCharge)) / Constants.HUNDRED;
    }
}
