import model.entities.incomes.additional_incomes.AuthorRewardIncome;
import model.entities.incomes.additional_incomes.GiftIncome;
import model.entities.incomes.additional_incomes.TransferIncome;
import model.entities.incomes.work_incomes.AdditionalWorkIncome;
import model.entities.taxes.Tax;
import model.entities.taxes.TransferIncomeTax;
import model.entities.taxes.WorkIncomeTax;

/**
 * Created by troll on 15.08.2017.
 */
public class BuildProgram {

    public static void main(String[] args) {
        Tax tax = new WorkIncomeTax(new AdditionalWorkIncome(), 20, 1.5);
        System.out.print(GiftIncome.class.getSimpleName());
    }
}
