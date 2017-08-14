package model.entities.users;

import model.entities.incomes.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by troll on 14.08.2017.
 */
public class User {
    private String email;
    private String password;

    private List<Income> userIncomes = new ArrayList<>();

    User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void addIncome(Income income){
        userIncomes.add(income);
    }

    public void removeIncome(Income income){
        userIncomes.remove(income);
    }

}
