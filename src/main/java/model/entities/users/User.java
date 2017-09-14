package model.entities.users;

import model.entities.incomes.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by troll on 14.08.2017.
 */
public class User {

    private Integer id;
    private String email;
    private String password;

    private boolean isAdmin;

    private List<Income> userIncomes = new ArrayList<>();

    public User(String email, String password){
        this.email = email;
        this.password = password;
        isAdmin = false;
    }

    public void addIncome(Income income){
        userIncomes.add(income);
    }

    public void removeIncome(Income income){
        userIncomes.remove(income);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
