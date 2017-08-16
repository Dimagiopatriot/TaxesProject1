package model.entities.taxes;

import model.entities.incomes.Income;

/**
 * Created by troll on 14.08.2017.
 */
public class TransferIncomeTax extends Tax implements TaxCalculator {

    public TransferIncomeTax(Income transferIncome, double taxPercent){
       super(transferIncome, taxPercent);
    }
}
