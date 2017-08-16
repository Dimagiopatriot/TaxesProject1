package model.entities.taxes;

import model.entities.incomes.WorkIncome;
import model.entities.utils.Constants;

/**
 * Created by troll on 13.08.2017.
 */
public class WorkIncomeTax extends Tax implements TaxCalculator {

    private double militaryCharge;

    public WorkIncomeTax(WorkIncome workIncome, double taxPercent, double militaryCharge) {
        super(workIncome, taxPercent);
        this.militaryCharge = militaryCharge;
    }

    public void calculateTax() {
        taxNeedToPay = Constants.MONTHS_IN_YEAR * ((income.getIncome() * taxPercent) + (income.getIncome() * militaryCharge)) / Constants.HUNDRED;
    }
}
