package model.entities.incomes;

/**
 * Created by troll on 16.08.2017.
 */
public class IncomeBuilder {
    private int id = 0;
    private double incomeMoney = 0.;
    private boolean isPerMonth = false;
    private int taxId = 0;
    private int userId = 0;
    private String name = "";

    public IncomeBuilder setId(int id){
        this.id = id;
        return this;
    }

    public IncomeBuilder setIncome(double incomeMoney){
        this.incomeMoney = incomeMoney;
        return this;
    }

    public IncomeBuilder setIsPerMonth(boolean isPerMonth){
        this.isPerMonth = isPerMonth;
        return this;
    }

    public IncomeBuilder setTaxId(int taxId){
        this.taxId = taxId;
        return this;
    }

    public IncomeBuilder setUserId(int userId){
        this.userId = userId;
        return this;
    }

    public IncomeBuilder setName(String name){
        this.name = name;
        return this;
    }

    public Income createIncome(){
        Income income;
        if (isPerMonth){
            income = new WorkIncome();
        } else {
            income = new AdditionalIncome();
        }
        income.setId(id);
        income.setIncome(incomeMoney);
        income.setPerMonth(isPerMonth);
        income.setTaxId(taxId);
        income.setUserId(userId);
        income.setName(name);

        return income;
    }

}
