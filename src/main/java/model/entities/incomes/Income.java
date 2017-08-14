package model.entities.incomes;

/**
 * Created by troll on 13.08.2017.
 */
public class Income {

    private int id;
    private double income;
    protected boolean isPerMonth;
    int taxId;
    int userId;
    private String name;

    public Income(){
        name = getClass().getSimpleName();
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public boolean isPerMonth() {
        return isPerMonth;
    }

    public void setPerMonth(boolean perMonth) {
        isPerMonth = perMonth;
    }

    public String getName() {
        return name;
    }
}
