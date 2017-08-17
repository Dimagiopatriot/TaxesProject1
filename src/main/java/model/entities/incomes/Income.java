package model.entities.incomes;

/**
 * Created by troll on 13.08.2017.
 */
public class Income {

    private int id;
    private double income;
    boolean isPerMonth;
    private int taxId;
    private int userId;
    private String name;

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

    public int getUserId() {
        return userId;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
