package model.entities.taxes;

import model.entities.incomes.additional_incomes.MaterialAidIncome;
import model.entities.utils.Constants;

/**
 * Created by troll on 14.08.2017.
 */
public class MaterialAidIncomeTax extends Tax {

    private double militaryCharge;

    public MaterialAidIncomeTax(MaterialAidIncome materialAidIncome, double taxPercent, double militaryCharge) {
        super(materialAidIncome, taxPercent);
        this.militaryCharge = militaryCharge;
    }

    public void calculateTax() {
        taxNeedToPay = ((income.getIncome() * taxPercent) + (income.getIncome() * militaryCharge)) / Constants.HUNDRED;
    }
}
