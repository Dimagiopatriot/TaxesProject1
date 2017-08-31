package controller.utils;

import model.dao.IncomeDaoInterface;
import model.dao.exceptions.DaoException;
import model.dao.impl.IncomeDao;
import model.entities.incomes.Income;

import java.util.ArrayList;
import java.util.List;

public class IncomeRetrieve {

    private List<Income> userIncomes = new ArrayList<>();

    public IncomeRetrieve(int userId){
        retrieveIncomesByUserId(userId);
    }

    private void retrieveIncomesByUserId(int userId){
        IncomeDaoInterface incomeDao = new IncomeDao();
        try {
            userIncomes = incomeDao.selectAllIncomesForUser(userId);
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
