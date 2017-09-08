package controller.utils;

import model.dao.exceptions.DaoException;
import model.entities.incomes.Income;
import model.service.IncomeService;

import java.util.ArrayList;
import java.util.List;

public class IncomeRetrieve {

    private List<Income> userIncomes = new ArrayList<>();

    public IncomeRetrieve(int userId){
        retrieveIncomesByUserId(userId);
    }

    private void retrieveIncomesByUserId(int userId){
        IncomeService service = IncomeService.getInstance();
        try {
            userIncomes = service.selectAllIncomesForUser(userId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public double retrieveIncomeValueByType(String incomeType){
        for (Income income : userIncomes){
            if (income.getName().equals(incomeType)){
                return income.getIncome();
            }
        }
        return 0.;
    }
}
