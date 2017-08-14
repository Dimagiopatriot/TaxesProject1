package model.entities.taxes;

import model.entities.incomes.additional_incomes.TransferIncome;

/**
 * Created by troll on 14.08.2017.
 */
public class TransferIncomeTax extends Tax implements TaxCalculator {

    public TransferIncomeTax(TransferIncome transferIncome, double taxPercent){
       super(transferIncome, taxPercent);
    }
}
